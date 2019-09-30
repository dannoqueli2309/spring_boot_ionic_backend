package com.aplicacao.cursomc.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aplicacao.cursomc.domain.Cidade;
import com.aplicacao.cursomc.domain.Cliente;
import com.aplicacao.cursomc.domain.Endereco;
import com.aplicacao.cursomc.domain.enums.TipoCliente;
import com.aplicacao.cursomc.dto.ClienteDTO;
import com.aplicacao.cursomc.dto.ClienteDtoEnderecoTelefone;
import com.aplicacao.cursomc.repositories.CidadeRepository;
import com.aplicacao.cursomc.repositories.ClienteRepository;
import com.aplicacao.cursomc.repositories.EnderecoRepository;
import com.aplicacao.cursomc.services.exceptions.DataIntegrateException;
import com.aplicacao.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRespository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente>clientes = clienteRespository.findById(id);
		return clientes.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id"+ id +", Tipo: "+ Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		clienteRespository.save(cliente);
		enderecoRepository.saveAll(cliente.getEndereco());
		return cliente;
	}

	public Cliente update(Cliente cliente) {
		Cliente clienteNew = buscar(cliente.getId());
		updateData(clienteNew,cliente);
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
	
	public Cliente fromDTO(ClienteDtoEnderecoTelefone clienteDTO) {
		Cliente cliente = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfCnpj(), TipoCliente.toEnum(clienteDTO.getTipoCliente()));
		
		cliente.getEndereco().add(getEnderecoComCidadePeloClienteDtoEnderecoTelefone(clienteDTO, cliente));		
		
		cliente.getTelefones().add(clienteDTO.getTelefone());
		
		if(Objects.nonNull(clienteDTO.getTelefone2())) {
			cliente.getTelefones().add(clienteDTO.getTelefone2());
		}
		
		if(Objects.nonNull(clienteDTO.getTelefone3())) {
			cliente.getTelefones().add(clienteDTO.getTelefone3());
		}
		
		return cliente;
		
	}

	private Endereco getEnderecoComCidadePeloClienteDtoEnderecoTelefone(ClienteDtoEnderecoTelefone clienteDTO, Cliente cliente) {
		
		Cidade cidade = cidadeRepository.findById(clienteDTO.getCidadeId()).get();
		return new Endereco(null,clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento(), clienteDTO.getBairro(), clienteDTO.getCep(), cidade, cliente);
	}

	private void updateData(Cliente clienteNew, Cliente clienteOld) {
		clienteNew.setNome(clienteOld.getNome());
		clienteNew.setEmail(clienteOld.getEmail());
	}
	
	
}