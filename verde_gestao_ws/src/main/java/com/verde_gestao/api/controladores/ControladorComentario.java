package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.Comentario;
import com.verde_gestao.api.repositorios.RepositorioComentario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ControladorComentario {

    private final RepositorioComentario repositorioComentario;

    public ControladorComentario(RepositorioComentario repositorioComentario) {
        this.repositorioComentario = repositorioComentario;
    }

    @GetMapping("/solicitacao/{solicitacaoId}")
    public List<Comentario> getComentariosPorSolicitacao(@PathVariable("solicitacaoId") int solicitacaoId) {
        return repositorioComentario.buscarComentariosPorSolicitacao(solicitacaoId);
    }

    @GetMapping("/{id}")
    public Comentario getComentarioPorId(@PathVariable("id") int comentarioId) {
        return repositorioComentario.buscarComentarioPorId(comentarioId);
    }

    @PostMapping
    public String inserirComentario(@RequestBody Comentario comentario) {
        int result = repositorioComentario.inserirComentario(comentario);
        return result > 0 ? "Comentário inserido com sucesso!" : "Erro ao inserir comentário.";
    }

    @PutMapping("/{id}")
    public String atualizarComentario(@PathVariable("id") int comentarioId, @RequestBody Comentario comentario) {
        comentario.setComentarioId(comentarioId);
        int result = repositorioComentario.atualizarComentario(comentario);
        return result > 0 ? "Comentário atualizado com sucesso!" : "Erro ao atualizar comentário.";
    }

    @DeleteMapping("/{id}")
    public String deletarComentario(@PathVariable("id") int comentarioId) {
        int result = repositorioComentario.deletarComentario(comentarioId);
        return result > 0 ? "Comentário deletado com sucesso!" : "Erro ao deletar comentário.";
    }

}