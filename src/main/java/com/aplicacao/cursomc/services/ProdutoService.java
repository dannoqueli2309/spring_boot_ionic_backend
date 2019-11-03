package com.aplicacao.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aplicacao.cursomc.domain.Categoria;
import com.aplicacao.cursomc.domain.Produto;
import com.aplicacao.cursomc.repositories.CategoriaRepository;
import com.aplicacao.cursomc.repositories.ProdutoRepository;
import com.aplicacao.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto buscar(Integer id){
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id"+id+", Tipo: "+Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest  = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pageRequest);
	}
}
