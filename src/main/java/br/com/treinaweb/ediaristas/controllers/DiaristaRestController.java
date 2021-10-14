package br.com.treinaweb.ediaristas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.models.Diarista;
import br.com.treinaweb.ediaristas.repositories.DiaristaRepository;

@RestController //controller da API
@RequestMapping("/api/diaristas-cidade")
public class DiaristaRestController {

    @Autowired
    private DiaristaRepository repository;

    @GetMapping // = action, metodo mapeado atraves de uma rota
    public List<Diarista> buscarDiaristasPorCep(){
        return repository.findAll();
    }
    
}
