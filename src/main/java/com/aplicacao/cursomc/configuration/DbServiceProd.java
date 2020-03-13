package com.aplicacao.cursomc.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class DbServiceProd implements DbService{

  public void populateDataBase(){}
}
