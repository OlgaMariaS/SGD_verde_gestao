package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.Solicitacao;
import com.verde_gestao.api.servicos.ServicoSolicitacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class ControladorSolicitacao {

    private final ServicoSolicitacao servicoSolicitacao;

    public ControladorSolicitacao(ServicoSolicitacao servicoSolicitacao) {
        this.servicoSolicitacao = servicoSolicitacao;
    }

    @GetMapping
    public List<Solicitacao> buscarTodos() {
        return servicoSolicitacao.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoSolicitacao.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Solicitacao> criar(@RequestBody Solicitacao objeto) {
        return ResponseEntity.ok(servicoSolicitacao.criar(objeto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitacao> atualizar(@PathVariable Long id, @RequestBody Solicitacao objeto) {
        return ResponseEntity.ok(servicoSolicitacao.atualizar(id, objeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoSolicitacao.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/minhasSolicitacoes/{usuarioId}")
    public List<Solicitacao> buscarMinhasSolicitacoes(@PathVariable Long usuarioId) {
        return servicoSolicitacao.buscarMinhasSolicitacoes(usuarioId);
    }

}
