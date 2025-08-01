package com.verde_gestao.api.repositorios;

import com.verde_gestao.api.objetos.modelo.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioComentario extends JpaRepository<Comentario, Long> {

}
