package br.com.treinaweb.ediaristas.converters;

import javax.persistence.AttributeConverter;

public class CpfConverter implements AttributeConverter<String, String>{

    @Override
    public String convertToDatabaseColumn(String cpf) {
        return cpf.replaceAll("[.-]", ""); //replaceAll: expressao regular do que deve ser removido
    }

    @Override
    public String convertToEntityAttribute(String cpf) {
        return cpf;
    }
    
}
