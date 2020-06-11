package com.aplicacao.cursomc.security;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private JwtUtil jwtUtil;
  private UserDetailsService userDetailsService;

  // Analisa o token para ver se o token é valido
  public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
    super(authenticationManager);
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
  }

  // recupera o token no header da request
  // valida se o token enviado no  header é um token valido.
  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
      // retorna um token do spring security caso o token JWT seja valido.
      UsernamePasswordAuthenticationToken auth = getAuthenticationToken(request,authorizationHeader.substring(7));

      if(Objects.nonNull(auth)){
        //validando o token enviado no header.
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }
    // continua a chamada da cadeia de filtros.
    chain.doFilter(request,response);
  }

  // Recupera o token do spring security para autenticação.
  private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request, String token) {
    /**
     * 1° valida se o token é valido
     * 2° recupera o nome do usuario pelo token
     * 3° recupera o objeto userDetails pelo nome do usuario
     * 4° recupera o objeto UsernamePasswordAuthenticationToken informando, userDetails e os perfils(Granted)  por parametro.
     *  return token do spring security.
     */
    if(jwtUtil.tokenValido(token)){
     String username = jwtUtil.getUsername(token);
     UserDetails userDetails = userDetailsService.loadUserByUsername(username);
     return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
   }
    return null;
  }
}
