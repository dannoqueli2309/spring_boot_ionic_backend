package com.aplicacao.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aplicacao.cursomc.domain.Categoria;
import com.aplicacao.cursomc.repositories.CategoriaRepository;
import com.aplicacao.cursomc.services.exceptions.DataIntegrateException;
import com.aplicacao.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> categorias = repo.findById(id);
		return categorias.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado Id" + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repo.save(categoria);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrateException("Não é possivel excluir uma categoria que possua produtos vinculados");
		}
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
}