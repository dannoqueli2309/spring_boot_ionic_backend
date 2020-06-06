package com.aplicacao.cursomc.configuration;

import com.aplicacao.cursomc.security.JwtAuthenticationFilter;
import com.aplicacao.cursomc.security.JwtUtil;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private Environment environment;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  private static final String[] PUBLIC_MATCHERS = {
      "/h2-console/**"
  };

  private static final String[] PUBLIC_MATCHERS_GET = {
      "/produtos/**",
      "/categorias/**",
      "/clientes/**"
  };

  @Override
  public void configure(HttpSecurity http) throws Exception {

    // Desativando o FlameOptions para o banco h2.
    if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
      http.headers().frameOptions().disable();
    }

    http.cors().and().csrf().disable();

    http.authorizeRequests()
        .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
        .antMatchers(PUBLIC_MATCHERS).permitAll()
        .anyRequest().authenticated();
    http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil));
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder());
  }

  // Liberando requisições Cross-over.
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
