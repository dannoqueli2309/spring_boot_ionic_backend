package com.aplicacao.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aplicacao.cursomc.domain.Cliente;
import com.aplicacao.cursomc.dto.ClienteDTO;
import com.aplicacao.cursomc.dto.ClienteDtoEnderecoTelefone;
import com.aplicacao.cursomc.services.ClienteService;;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente cliente = clienteService.buscar(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insertCliente(@Valid @RequestBody ClienteDtoEnderecoTelefone clienteDTO) {
		Cliente cliente = clienteService.fromDTO(clienteDTO);
		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDto, @PathVariable Integer id){
		Cliente cliente = clienteService.fromDTO(clienteDto);
		cliente.setId(id);
		cliente = clienteService.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // protocolo HTTP tipo GET passando id
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET) // protocolo HTTP tipo GET passando id
	public ResponseEntity<List<ClienteDTO>> findAll() {
		 List<ClienteDTO> clientesDto = clienteService.findAll().stream()
		 .map(ClienteDTO::new)
		 .collect(Collectors.toList());
		
		return ResponseEntity.ok().body(clientesDto);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET) // protocolo HTTP tipo GET passando id
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<ClienteDTO> clienteDto = clienteService.findPage(page, linesPerPage, orderBy, direction)
				.map(ClienteDTO::new);

		return ResponseEntity.ok().body(clienteDto);
	}
}
