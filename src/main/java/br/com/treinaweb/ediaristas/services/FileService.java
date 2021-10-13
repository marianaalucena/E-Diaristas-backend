package br.com.treinaweb.ediaristas.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    
    private final Path pastaUpload = Paths.get("uploads");

    public String salvar(MultipartFile file) throws IOException{
        if(!Files.exists(pastaUpload)){
            Files.createDirectories(pastaUpload);
        }

        var originalFilename = file.getOriginalFilename();
        var ext = originalFilename.split("\\.")[1]; //pegando apenas a extensao do arquivo
        var filename = UUID.randomUUID().toString() + "." + ext; //gerando novo nome do arquivo

        Files.copy(file.getInputStream(), pastaUpload.resolve(filename)); //cria um arquivo dentro de upload e no arquivo coloca o conteudo recebido
        
        return filename;
    }


    public Resource carregar(String filename) throws MalformedURLException{
        var filePath = pastaUpload.resolve(filename); //caminho do arquivo
        return new UrlResource(filePath.toUri());
    }


}
