package com.aplicacao.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aplicacao.cursomc.domain.Categoria;
import com.aplicacao.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)// protocolo HTTP tipo GET passando id
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria categoriaService =service.buscar(id);
		return ResponseEntity.ok().body(categoriaService);
	}
}
