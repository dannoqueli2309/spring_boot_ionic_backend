package com.aplicacao.cursomc.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbPopulateConfig {

  @Bean
  @Qualifier("dbTest")
  public DbService populateDb() {
    return new DbServiceTest();
  }
}
