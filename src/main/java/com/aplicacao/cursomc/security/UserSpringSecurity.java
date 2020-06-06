package com.aplicacao.cursomc.security;

import com.aplicacao.cursomc.domain.enums.Perfil;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSpringSecurity implements UserDetails {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String email;
  private String senha;
  private Collection<? extends GrantedAuthority> grantedAuthorities;

  public UserSpringSecurity() {

  }

  public UserSpringSecurity(Integer id, String email, String senha, Set<Perfil> perfils) {
    this.id = id;
    this.email = email;
    this.senha = senha;
    this.grantedAuthorities = perfils
        .stream()
        .map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
        .collect(Collectors.toSet());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return senha;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
