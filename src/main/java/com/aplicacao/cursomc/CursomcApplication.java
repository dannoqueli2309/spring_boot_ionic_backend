package com.aplicacao.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aplicacao.cursomc.configuration.DbService;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private DbService dbService;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dbService.populateDataBase();
	}
}
