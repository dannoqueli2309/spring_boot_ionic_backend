package com.aplicacao.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.aplicacao.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "preenchimento obrigatorio")
	@Length(min=5, max=80, message = "O tamanho deve ser entre 5 a 80 caracteres")
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static CategoriaDTO converterCategoriaDto(Categoria categoria) {
		CategoriaDTO categoriaDto = new CategoriaDTO();
		categoriaDto.setId(categoria.getId());
		categoriaDto.setNome(categoria.getNome());
		return categoriaDto;
	}
}