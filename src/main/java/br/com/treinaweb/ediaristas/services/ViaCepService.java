package br.com.treinaweb.ediaristas.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.treinaweb.ediaristas.dtos.ViaCepResponse;
import br.com.treinaweb.ediaristas.exceptions.CepInvalidoException;
import br.com.treinaweb.ediaristas.exceptions.CepNaoEncontradoException;

@Service
public class ViaCepService {

    public ViaCepResponse buscarEnderecoPorCep(String cep){
        var url = "https://viacep.com.br/ws/"+ cep + "/json/";

        var clienteHttp = new RestTemplate(); //classe que o spring disponibiliza para fazer requisições http
        ResponseEntity<ViaCepResponse> response; 
        
        try {
            response = clienteHttp.getForEntity(url, ViaCepResponse.class);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new CepInvalidoException("CEP inválido!");
        }

        if(response.getBody().getCep() == null){
            throw new CepNaoEncontradoException("CEP não encontrado!");
        }
        

        return response.getBody(); //pega o corpo da resposta que é um objeto do tipo ViaCepResponse
    }
    
}
