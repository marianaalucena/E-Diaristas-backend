package br.com.treinaweb.ediaristas.converters;

import javax.persistence.AttributeConverter;

//convertendo um dado antes de enviar para a base de dados
public class CepConverter implements AttributeConverter<String, String>{

    //convertToDatabaseColumn: o que vai acontecer com o dado no momento que ele for salvo
    @Override
    public String convertToDatabaseColumn(String cep) {
        return cep.replace("-", "");
    }

    //convertToEntityAttribute: o que vai acontecer com o dado no momento que for carregado do bd
    @Override
    public String convertToEntityAttribute(String cep) {
        return cep;
    }


    
}
