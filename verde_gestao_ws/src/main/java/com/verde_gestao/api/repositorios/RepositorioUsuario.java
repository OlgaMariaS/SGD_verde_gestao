package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.objetos.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNomeAndSenha(String nome, String senha);

    @Query("""
        SELECT u
        FROM Usuario u
        JOIN u.tipousuario t
        WHERE (
            u.usuarioid = :usuarioId
            OR LOWER(:tipoUsuarioDescricao) = 'ti'
            OR (
                LOWER(:tipoUsuarioDescricao) = 'chefe diretor'
            )
            OR (
                LOWER(:tipoUsuarioDescricao) = 'chefe de seção'
                AND LOWER(t.descricao) IN ('chefe diretor', 'chefe de assistente', 'ti')
            )
            OR (
                LOWER(:tipoUsuarioDescricao) = 'chefe de assistente'
                AND LOWER(t.descricao) IN ('chefe de seção', 'ti')
            )
        )
    """)

    List<Usuario> findByHierarquia(
            @Param("usuarioId") Long usuarioId,
            @Param("tipoUsuarioDescricao") String tipoUsuarioDescricao
    );
}