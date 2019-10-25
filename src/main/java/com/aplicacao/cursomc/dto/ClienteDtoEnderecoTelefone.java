package com.aplicacao.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.aplicacao.cursomc.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteDtoEnderecoTelefone implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotEmpty(message="preenchimento obrigatorio")
	@Length(min=5, max=120, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	@NotEmpty(message = "o email não pode estar vazio")
	@Email(message = "email invalid")
	private String email;
	
	@NotEmpty(message="preenchimento obrigatorio")
	private String cpfCnpj;

	private Integer tipoCliente;

	private Integer id;

	@NotEmpty(message="preenchimento obrigatorio")
	private String logradouro;

	@NotEmpty(message="preenchimento obrigatorio")
	private String numero;

	private String complemento;

	private String bairro;

	@NotEmpty(message="preenchimento obrigatorio")
	private String cep;

	@NotEmpty(message="preenchimento obrigatorio")
	private String telefone;

	private String telefone2;

	private String telefone3;

	private Integer cidadeId;

	public ClienteDtoEnderecoTelefone() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}


}
