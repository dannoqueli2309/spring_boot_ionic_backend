package com.aplicacao.cursomc.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aplicacao.cursomc.domain.Produto;
import com.aplicacao.cursomc.dto.ProdutoDto;
import com.aplicacao.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)// protocolo HTTP tipo GET passando id
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto produto = service.buscar(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@RequestMapping(method = RequestMethod.GET) 
	public ResponseEntity<Page<ProdutoDto>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias") Integer[] categorias) {
		
		List<Integer> listCategorieIds = Objects.nonNull(categorias) ?  Arrays.asList(categorias) : Collections.emptyList();
		
		Page<ProdutoDto> categoriasDto = service.search(nome, listCategorieIds, page, linesPerPage, orderBy, direction)
				.map(ProdutoDto::new);

		return ResponseEntity.ok().body(categoriasDto);
	}

}
