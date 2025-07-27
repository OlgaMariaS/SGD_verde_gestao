package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.objetos.modelo.Solicitacao;
import com.verde_gestao.api.objetos.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioSolicitacao extends JpaRepository<Solicitacao, Long> {

    @Query("""
        select s
        from Solicitacao s
        where s.status = 'ABERTA'
          and (s.criador.usuarioid = :usuario or s.responsavel.usuarioid = :usuario)
    """)
    List<Solicitacao> findByResponsavelAndCriador(@Param("usuario") Long usuarioId);

}
