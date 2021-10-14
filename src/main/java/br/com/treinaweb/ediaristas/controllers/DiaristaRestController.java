package br.com.treinaweb.ediaristas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.models.Diarista;
import br.com.treinaweb.ediaristas.repositories.DiaristaRepository;
import br.com.treinaweb.ediaristas.services.ViaCepService;

@RestController //controller da API
@RequestMapping("/api/diaristas-cidade")
public class DiaristaRestController {

    //injecao de dependencias
    @Autowired
    private DiaristaRepository repository;

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping // = action, metodo mapeado atraves de uma rota
    public List<Diarista> buscarDiaristasPorCep(@RequestParam String cep){
        var endereco = viaCepService.buscarEnderecoPorCep(cep);
        var codigoIbge = endereco.getIbge();

        return repository.findByCodigoIbge(codigoIbge);
    }
    
}
