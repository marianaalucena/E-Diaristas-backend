package br.com.treinaweb.ediaristas.exceptions;

public class CepInvalidoException extends RuntimeException{ //para criar uma exception basta extender de outra exception
    
    public CepInvalidoException(String message){
        super(message);
    }

}
