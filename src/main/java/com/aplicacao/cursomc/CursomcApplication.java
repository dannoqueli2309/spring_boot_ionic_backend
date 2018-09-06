package com.aplicacao.cursomc;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aplicacao.cursomc.domain.Categoria;
import com.aplicacao.cursomc.domain.Cidade;
import com.aplicacao.cursomc.domain.Cliente;
import com.aplicacao.cursomc.domain.Endereco;
import com.aplicacao.cursomc.domain.Estado;
import com.aplicacao.cursomc.domain.Produto;
import com.aplicacao.cursomc.domain.enums.TipoCliente;
import com.aplicacao.cursomc.repositories.CategoriaRepository;
import com.aplicacao.cursomc.repositories.CidadeRepository;
import com.aplicacao.cursomc.repositories.ClienteRepository;
import com.aplicacao.cursomc.repositories.EnderecoRepository;
import com.aplicacao.cursomc.repositories.EstadoRepository;
import com.aplicacao.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadesRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto prod1 = new Produto(null,"Computador",2000.00);
		Produto prod2 = new Produto(null,"Impressora",800.00);
		Produto prod3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1,prod2,prod3));// Arrays. asList passa varios elementos 
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2,c3));
	
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadesRepository.saveAll(Arrays.asList(c1,c2,c3));
		Cliente cli1 = new Cliente(null,"Maria Slva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323","938383030"));
		
		Endereco e1 = new Endereco(null,"rua Flores","300","apto 303","Jardim","38220834",c1,cli1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",c2,cli1);
		
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}
}
