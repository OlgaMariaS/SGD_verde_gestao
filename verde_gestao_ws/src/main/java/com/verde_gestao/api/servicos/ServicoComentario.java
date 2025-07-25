package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.Comentario;
import com.verde_gestao.api.repositorios.RepositorioComentario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoComentario {

    private final RepositorioComentario repositorioComentario;

    public ServicoComentario(RepositorioComentario repositorioComentario) {
        this.repositorioComentario = repositorioComentario;
    }

    public List<Comentario> buscarTodos() {
        return repositorioComentario.findAll();
    }

    public Optional<Comentario> buscarPorId(Long id) {
        return repositorioComentario.findById(id);
    }

    public Comentario salvar(Comentario objeto) {
        return repositorioComentario.save(objeto);
    }

    public void excluirPorId(Long id) {
        repositorioComentario.deleteById(id);
    }
}
