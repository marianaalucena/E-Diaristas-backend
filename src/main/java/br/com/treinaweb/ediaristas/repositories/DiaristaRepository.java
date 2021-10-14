package br.com.treinaweb.ediaristas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.models.Diarista;

public interface DiaristaRepository extends JpaRepository<Diarista, Long>{

    //é criada uma query com base no nome do metodo + retorno de metodo
    //findBy: busca a partir do CodigoIbge, funciona porque na classe Diarista ha um atributo com o mesmo nome codigoIbge. Retorna uma lista.
    Page<Diarista> findByCodigoIbge(String codigoIbge, Pageable pageable); //pageable: representa uma configuracao de paginacao

    //List<Diarista> findByCodigoIbge(String codigoIbge);
    
}