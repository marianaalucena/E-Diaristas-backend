package br.com.treinaweb.ediaristas.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.treinaweb.ediaristas.models.Diarista;
import br.com.treinaweb.ediaristas.services.ViaCepService;

@Component //define um bean generico para que seja possivel a injecao de dependencias, que só é possivel em classes com @Service ou @Controller
public class CepValidator implements Validator {

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Diarista.class.isAssignableFrom(clazz); //verifica se o objeto que ta sendo recebido é um objeto da classe Diarista
    }

    @Override
    public void validate(Object target, Errors errors) {
        var diarista = (Diarista) target; //convertendo Objeto target em Diarista

        try {
            viaCepService.buscarEnderecoPorCep(diarista.getCep());
        } catch (RuntimeException e) { //se viaCepService retorna um erro, RuntimeException erro implementado nos dois tipos de exception criado para cep
            errors.rejectValue("cep", null, e.getMessage());
        }
    }
    
}
