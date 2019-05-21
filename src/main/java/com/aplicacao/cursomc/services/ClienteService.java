package com.aplicacao.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aplicacao.cursomc.domain.Cliente;
import com.aplicacao.cursomc.dto.ClienteDTO;
import com.aplicacao.cursomc.repositories.ClienteRepository;
import com.aplicacao.cursomc.services.exceptions.DataIntegrateException;
import com.aplicacao.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRespository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente>clientes = clienteRespository.findById(id);
		return clientes.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id"+id+", Tipo: "+Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return clienteRespository.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		Cliente clienteNew = buscar(cliente.getId());
		updateNew(clienteNew,cliente);
		return clienteRespository.save(clienteNew);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			clienteRespository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrateException("Não é possivel excluir porque a entidades relacionadas");
		}
	}
	
	public List<Cliente> findAll() {
		return clienteRespository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest  = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRespository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null,null);
	}
	
	private void updateNew(Cliente clienteNew, Cliente clienteOld) {
		clienteNew.setNome(clienteOld.getNome());
		clienteNew.setEmail(clienteOld.getEmail());
	}
}