package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.objetos.modelo.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.verde_gestao.api.objetos.dto.CardAvisoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioAviso extends JpaRepository<Aviso, Long> {

    @Query("""
        SELECT new com.verde_gestao.api.objetos.dto.CardAvisoDTO(
            a.avisoid,
            a.titulo,
            a.texto,
            u.nome,
            to_char(a.dataInicio, 'DD/MM/YYYY')
        )
        FROM Aviso a
        JOIN a.autor u
        ORDER BY a.avisoid
    """)
    List<CardAvisoDTO> buscarTodosCardsAvisos();

}
