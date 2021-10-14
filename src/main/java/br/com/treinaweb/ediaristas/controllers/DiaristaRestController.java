package br.com.treinaweb.ediaristas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.dtos.DiaristasPagedResponse;
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
    public DiaristasPagedResponse buscarDiaristasPorCep(@RequestParam String cep){
        var endereco = viaCepService.buscarEnderecoPorCep(cep);
        var codigoIbge = endereco.getIbge();

        var pageable = PageRequest.of(0, 6); //PageRequest.of(numeroPagina, numeroElementosPorPagina); 0 = primeira pagina
        var diaristas = repository.findByCodigoIbge(codigoIbge, pageable);

        var quantidadeDiaristas = diaristas.getTotalElements() > 6 ? diaristas.getTotalElements() - 6 : 0; //quantidade que restam alem dos 6 que foram exibidos

        return new DiaristasPagedResponse(diaristas.getContent(), quantidadeDiaristas);

        //return repository.findByCodigoIbge(codigoIbge);
    }
    
}
