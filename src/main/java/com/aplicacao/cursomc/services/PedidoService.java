package com.aplicacao.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacao.cursomc.domain.Pedido;
import com.aplicacao.cursomc.repositories.PedidoRepository;
import com.aplicacao.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id){
		Optional<Pedido> pedidos = repo.findById(id);
		return pedidos.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id"+id+", Tipo: "+Pedido.class.getName()));
	}
}
