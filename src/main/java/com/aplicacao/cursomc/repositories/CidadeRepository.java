package com.aplicacao.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicacao.cursomc.domain.Categoria;
import com.aplicacao.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Integer>{

}
