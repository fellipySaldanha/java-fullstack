package com.java.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.java.domain.enums.ClientType;
import com.java.dto.ClientNewDto;
import com.java.resources.exception.FieldMessage;
import com.java.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDto> {
    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDto clientDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        
        if(clientDto.getType().equals(ClientType.PESSOAFISICA.getCode()) && !BR.isValidCPF(clientDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "CPF Inválido"));
        }

        if(clientDto.getType().equals(ClientType.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(clientDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "CNPJ Inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}