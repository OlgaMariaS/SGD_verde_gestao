package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.objetos.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNomeAndSenha(String nome, String senha);

}
