package com.aplicacao.cursomc.configuration;

import java.text.ParseException;
import org.springframework.stereotype.Service;

@Service
public interface DbService {
  void populateDataBase() throws ParseException;
}
