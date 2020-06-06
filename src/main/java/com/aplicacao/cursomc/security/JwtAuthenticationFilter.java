package com.aplicacao.cursomc.security;

import com.aplicacao.cursomc.dto.CredenciaisDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  private JwtUtil jwtUtil;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    try {

      // vai recuperar as informações do body do objeto de request e converter para para um objeto de CredenciaisDto
      CredenciaisDto credenciaisDto = new ObjectMapper()
          .readValue(request.getInputStream(), CredenciaisDto.class);

      // Token do spring security.
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(credenciaisDto.getEmail(),
              credenciaisDto.getSenha(),
              new ArrayList<>());

      // metodo que faz a autenticação
      Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

      return authenticate;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {

    String username = ((UserSpringSecurity) authResult.getPrincipal()).getUsername();
    String token = jwtUtil.generateToken(username);

    response.addHeader("Authorization", "Bearer" + token);
  }

  private class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
        throws IOException, ServletException {
      response.setStatus(401);
      response.setContentType("application/json");
      response.getWriter().append(json());
    }

    private String json() {
      return "{\"timestamp\": " + new Date().getTime() + ", "
          + "\"status\": 401, "
          + "\"error\": \"Não autorizado\", "
          + "\"message\": \"Email ou senha inválidos\", "
          + "\"path\": \"/login\"}";
    }
  }
}
