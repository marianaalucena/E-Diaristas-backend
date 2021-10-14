package br.com.treinaweb.ediaristas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //configurando o cors para todas as rotas /**
        .allowedMethods("*") //aceita receber qualquer metodo http *
        .allowedOrigins("*"); //compartilha o recurso com todo mundo
    } 
    
}
