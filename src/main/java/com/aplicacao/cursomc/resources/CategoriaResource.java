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

import com.aplicacao.cursomc.domain.Categoria;
import com.aplicacao.cursomc.dto.CategoriaDTO;
import com.aplicacao.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // protocolo HTTP tipo GET passando id
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria categoriaService = service.buscar(id);
		return ResponseEntity.ok().body(categoriaService);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = service.fromDTO(categoriaDTO);
		categoria = service.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
		Categoria categoria = service.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoria = service.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // protocolo HTTP tipo GET passando id
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET) // protocolo HTTP tipo GET passando id
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		 List<CategoriaDTO> categoriasDto = service.findAll().stream()
		 .map(CategoriaDTO::new)
		 .collect(Collectors.toList());
		
		return ResponseEntity.ok().body(categoriasDto);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET) // protocolo HTTP tipo GET passando id
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<CategoriaDTO> categoriasDto = service.findPage(page, linesPerPage, orderBy, direction)
				.map(CategoriaDTO::new);

		return ResponseEntity.ok().body(categoriasDto);
	}


}
