package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.Comentario;
import com.verde_gestao.api.servicos.ServicoComentario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ControladorComentario {

    private final ServicoComentario servicoComentario;

    public ControladorComentario(ServicoComentario servicoComentario) {
        this.servicoComentario = servicoComentario;
    }

    @GetMapping
    public List<Comentario> buscarTodos() {
        return servicoComentario.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoComentario.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Comentario> salvar(@RequestBody Comentario objeto) {
        return ResponseEntity.ok(servicoComentario.salvar(objeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoComentario.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

}
