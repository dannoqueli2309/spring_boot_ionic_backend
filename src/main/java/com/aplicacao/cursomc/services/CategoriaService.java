package com.aplicacao.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aplicacao.cursomc.domain.Categoria;
import com.aplicacao.cursomc.dto.CategoriaDTO;
import com.aplicacao.cursomc.repositories.CategoriaRepository;
import com.aplicacao.cursomc.services.exceptions.DataIntegrateException;
import com.aplicacao.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> categorias = categoriaRepo.findById(id);
		return categorias.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado Id" + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepo.save(categoria);
	}

	public Categoria update(Categoria categoria) {
	Categoria  categoriaNew = buscar(categoria.getId());
	updateData(categoriaNew, categoria);
		return categoriaRepo.save(categoria);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			categoriaRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrateException("Não é possivel excluir uma categoria que possua produtos vinculados");
		}
	}
	
	public List<Categoria> findAll() {
		return categoriaRepo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest  = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(),categoriaDTO.getNome());
	}
	
	private void updateData(Categoria CategoriaNewObject, Categoria categoriaObject) {
		CategoriaNewObject.setNome(categoriaObject.getNome());
	}
}