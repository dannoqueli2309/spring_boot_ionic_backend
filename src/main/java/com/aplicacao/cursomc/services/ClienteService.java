package com.aplicacao.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacao.cursomc.domain.Cliente;
import com.aplicacao.cursomc.repositories.ClienteRepository;
import com.aplicacao.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRespository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente>clientes = clienteRespository.findById(id);
		return clientes.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id"+id+", Tipo: "+Cliente.class.getName()));
	}
}
