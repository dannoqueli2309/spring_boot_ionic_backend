package com.aplicacao.cursomc.security;

import com.aplicacao.cursomc.domain.Cliente;
import com.aplicacao.cursomc.repositories.ClienteRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Cliente cliente = clienteRepository.findByEmail(email);

    if (Objects.isNull(cliente)) {
      throw new UsernameNotFoundException(email);
    }

    return new UserSpringSecurity(cliente.getId(),
        cliente.getEmail(),
        cliente.getSenha(),
        cliente.getPerfil());
  }
}
