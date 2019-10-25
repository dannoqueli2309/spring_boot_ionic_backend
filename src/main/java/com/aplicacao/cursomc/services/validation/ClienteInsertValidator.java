package com.aplicacao.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.aplicacao.cursomc.domain.enums.TipoCliente;
import com.aplicacao.cursomc.dto.ClienteDtoEnderecoTelefone;
import com.aplicacao.cursomc.resources.exception.FieldMessage;
import com.aplicacao.cursomc.services.validation.utils.CpfAndCnpjUtils;
/**
 * Esta classe valida a regra customizada para a anotação @ClienteInsert {@link Class ClienteInsert}
 * Caso o tipo de cliente seja PESSOAFISICA o cpf devera esta valido  
 * Caso o tipo de cliente seja PESSOAJURIDICA o cnpj devera esta valido 
 * @author DNQL
 *
 */
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDtoEnderecoTelefone>{
	@Override
	public void initialize(ClienteInsert clienteInsert) {
		
	}
	
	@Override
	public boolean isValid(ClienteDtoEnderecoTelefone clienteDtoEnderecoTelefone, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (Objects.isNull(clienteDtoEnderecoTelefone.getTipoCliente())) {
			list.add(new FieldMessage("tipo", "tipo não pode ser nulo"));
		}
		
		if (TipoCliente.PESSOAFISICA.getCod().equals(clienteDtoEnderecoTelefone.getTipoCliente()) && !CpfAndCnpjUtils.isValidCPF(clienteDtoEnderecoTelefone.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "cpf invalido"));
		}
		

		if (TipoCliente.PESSOJURIDICA.getCod().equals(clienteDtoEnderecoTelefone.getTipoCliente()) && !CpfAndCnpjUtils.isValidCNPJ(clienteDtoEnderecoTelefone.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "cnpj invalido"));
		}

		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();

	}

}
