package com.aplicacao.cursomc.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;

  public String generateToken(String username) {
    return Jwts
        .builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS512, secret.getBytes())
        .compact();

  }

  /**
   * Claims são reivindicações do usuario que quer validar  o token no caso o usuario diz ter um token com "reivindicações" Usuario e tempo de expiração.
   *
   * @return boolean true token valido ou false token invalido.
   */
  public boolean tokenValido(String token) {

    Claims claims = getClaims(token);

    if (Objects.nonNull(claims)) {

      String username = claims.getSubject();
      Date expirationDate = claims.getExpiration();
      Date currentDate = new Date(System.currentTimeMillis());

      if (Objects.nonNull(username)
          && Objects.nonNull(expirationDate)
          && currentDate.before(expirationDate)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Metodo para a recuperação dos claims
   * @param token
   * @return
   */
  private Claims getClaims(String token) {
    try {
      return Jwts.parser()
          .setSigningKey(secret.getBytes())
          .parseClaimsJws(token)
          .getBody();
    }catch (Exception exception){
      return null;
    }
  }

  /**
   * Observação: recupera do claims o subject==(userName)
   * @param token
   * @return String nome do usuário apartir do token
   */
  public String getUsername(String token) {
    Claims claims = getClaims(token);
    return Objects.nonNull(claims) ? claims.getSubject() : null;
  }

}
