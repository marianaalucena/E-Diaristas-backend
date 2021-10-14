package br.com.treinaweb.ediaristas.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.ediaristas.models.Diarista;
import br.com.treinaweb.ediaristas.repositories.DiaristaRepository;
import br.com.treinaweb.ediaristas.services.FileService;
import br.com.treinaweb.ediaristas.services.ViaCepService;

@Controller
@RequestMapping("/admin/diaristas") //endpoint para acessar esse controller
public class DiaristaController {

    //Injecao de dependencias, todos os @Autowired
    @Autowired
    private DiaristaRepository repository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping // /admin/diaristas - com isso essa action ja eh acessada
    public ModelAndView listar(){
        var modelAndView = new ModelAndView("admin/diaristas/listar");

        modelAndView.addObject("diaristas", repository.findAll());

        return modelAndView;
    }

    @GetMapping("/cadastrar") /// localhost:8080/admin/diaristas/cadastrar
    public ModelAndView  cadastrar() {//ModelAndView: informacoes da view que serao renderizadas e quais dados serao enviados para a view
        var modelAndView = new ModelAndView("admin/diaristas/form");

        modelAndView.addObject("diarista", new Diarista());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam MultipartFile imagem, @Valid Diarista diarista, BindingResult result) throws IOException{
        if(result.hasErrors()){
            return "admin/diaristas/form";        
        }
        //injencao de dependencias:
        var filename = fileService.salvar(imagem);
        diarista.setFoto(filename);

        var cep = diarista.getCep();
        var endereco = viaCepService.buscarEnderecoPorCep(cep);
        var codigoIbge = endereco.getIbge();
        diarista.setCodigoIbge(codigoIbge);

        repository.save(diarista);

        return "redirect:/admin/diaristas";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
        var modelAndView = new ModelAndView("admin/diaristas/form"); //renderiza a view de formulario

        modelAndView.addObject("diarista", repository.getById(id));

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@RequestParam MultipartFile imagem, @PathVariable Long id, @Valid Diarista diarista, BindingResult result) throws IOException{ //BindingResult deve vir após o dado que vai ser validado
        if(result.hasErrors()){
            return "admin/diaristas/form";        
        }

        var diaristaAtual = repository.getById(id);

        if(imagem.isEmpty()){ //nao ha edicao de imagem
            diarista.setFoto(diaristaAtual.getFoto());
        } else{ 
            var filename = fileService.salvar(imagem);
            diarista.setFoto(filename);
        }

        var cep = diarista.getCep();
        var endereco = viaCepService.buscarEnderecoPorCep(cep);
        var codigoIbge = endereco.getIbge();
        diarista.setCodigoIbge(codigoIbge);
        
        repository.save(diarista);

        return "redirect:/admin/diaristas";
    }

    @GetMapping("/{id}/excluir") // { } : identifica um dado que pode variar dentro da rota
    public String excluir(@PathVariable Long id){
        repository.deleteById(id);

        return "redirect:/admin/diaristas";
    }


}
