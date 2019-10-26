package com.aplicacao.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.aplicacao.cursomc.domain.Cliente;
import com.aplicacao.cursomc.dto.ClienteDTO;
import com.aplicacao.cursomc.repositories.ClienteRepository;
import com.aplicacao.cursomc.resources.exception.FieldMessage;

/**
 * Esta classe valida a regra customizada para a anotação @ClienteInsert
 * {@link Class ClienteUpdate} Caso o tipo de cliente seja PESSOAFISICA o cpf
 * devera esta valido Caso o tipo de cliente seja PESSOAJURIDICA o cnpj devera
 * esta valido
 * 
 * @author DNQL
 *
 */

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private ClienteRepository clienterepository;

	@Override
	public void initialize(ClienteUpdate clienteUpdate) {

	}

	@Override
	public boolean isValid(ClienteDTO clienteDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		@SuppressWarnings("unchecked")
		Map<String, String> mapVariable = (Map<String, String>) httpServletRequest
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer id = Integer.parseInt(mapVariable.get("id"));

		Cliente cliente = clienterepository.findByEmail(clienteDto.getEmail());

		if (Objects.nonNull(cliente) && !cliente.getId().equals(id)) {
			list.add(new FieldMessage("email", "Este email já existe na base"));
		}

		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();

	}

}
