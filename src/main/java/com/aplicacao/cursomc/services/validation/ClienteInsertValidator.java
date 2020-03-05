package com.aplicacao.cursomc.services.validation;

import static com.aplicacao.cursomc.domain.enums.TipoCliente.PESSOAFISICA;
import static com.aplicacao.cursomc.domain.enums.TipoCliente.PESSOJURIDICA;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.aplicacao.cursomc.domain.Cliente;
import com.aplicacao.cursomc.domain.enums.TipoCliente;
import com.aplicacao.cursomc.dto.ClienteDtoEnderecoTelefone;
import com.aplicacao.cursomc.repositories.ClienteRepository;
import com.aplicacao.cursomc.resources.exception.FieldMessage;
import com.aplicacao.cursomc.services.validation.utils.CpfAndCnpjUtils;

/**
 * Esta classe valida a regra customizada para a anotação @ClienteInsert
 * {@link Class ClienteInsert} Caso o tipo de cliente seja PESSOAFISICA o cpf
 * devera esta valido Caso o tipo de cliente seja PESSOAJURIDICA o cnpj devera
 * esta valido
 * 
 * @author DNQL
 *
 */
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDtoEnderecoTelefone> {

	@Autowired
	private ClienteRepository clienterepository;

	@Override
	public void initialize(ClienteInsert clienteInsert) {

	}

	@Override
	public boolean isValid(ClienteDtoEnderecoTelefone clienteDtoEnderecoTelefone, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		validationClienteType(clienteDtoEnderecoTelefone, list);

		isEmailExists(clienteDtoEnderecoTelefone, list);

		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();

	}

	private void validationClienteType(ClienteDtoEnderecoTelefone clienteDtoEnderecoTelefone, List<FieldMessage> list) {
		if (Objects.isNull(clienteDtoEnderecoTelefone.getTipo())) {
			list.add(new FieldMessage("tipo", "tipo não pode ser nulo"));
		}

		if (PESSOAFISICA.getCod().equals(clienteDtoEnderecoTelefone.getTipo())
				&& !CpfAndCnpjUtils.isValidCPF(clienteDtoEnderecoTelefone.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "cpf invalido"));
		}

		if (PESSOJURIDICA.getCod().equals(clienteDtoEnderecoTelefone.getTipo())
				&& !CpfAndCnpjUtils.isValidCNPJ(clienteDtoEnderecoTelefone.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "cnpj invalido"));
		}
	}

	private void isEmailExists(ClienteDtoEnderecoTelefone clienteDtoEnderecoTelefone, List<FieldMessage> list) {
		Cliente cliente = clienterepository.findByEmail(clienteDtoEnderecoTelefone.getEmail());

		if (Objects.nonNull(cliente)) {
			list.add(new FieldMessage("email", "Email já existe"));
		}
	}

}
