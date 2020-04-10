package com.aplicacao.cursomc.services;

import static com.aplicacao.cursomc.services.BoletoService.preencherPagamentoComBoleto;

import com.aplicacao.cursomc.domain.ItemPedido;
import com.aplicacao.cursomc.domain.PagamentoComBoleto;
import com.aplicacao.cursomc.domain.Produto;
import com.aplicacao.cursomc.domain.enums.EstadoPagamento;
import com.aplicacao.cursomc.repositories.ItemPedidosRepository;
import com.aplicacao.cursomc.repositories.PagamentoRepository;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacao.cursomc.domain.Pedido;
import com.aplicacao.cursomc.repositories.PedidoRepository;
import com.aplicacao.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidosRepository itemPedidosRepository;

	@Autowired
	private ClienteService clienteService;

	public Pedido buscar(Integer id){
		Optional<Pedido> pedidos = repository.findById(id);
		return pedidos.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id"+id+", Tipo: "+Pedido.class.getName()));
	}

	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstanteDate(new Date());
		pedido.setCliente(clienteService.buscar(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);

		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) pedido.getPagamento();
			preencherPagamentoComBoleto(pagamentoComBoleto, pedido.getInstanteDate());
		}

		pedido = repository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());

		for (ItemPedido ip : pedido.getItens()) {
			Produto produto = produtoService.buscar(ip.getProdutos().getId());
			ip.setProduto(produto);
			ip.setDesconto(0.0);
			ip.setPreco(produto.getPreco());
			ip.setPedidos(pedido);
		}
		itemPedidosRepository.saveAll(pedido.getItens());

		System.out.println("Pedido"+pedido);

		return pedido;

	}
}
