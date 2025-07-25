package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.Solicitacao;
import com.verde_gestao.api.servicos.ServicoSolicitacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacaos")
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
    public ResponseEntity<Solicitacao> salvar(@RequestBody Solicitacao objeto) {
        return ResponseEntity.ok(servicoSolicitacao.salvar(objeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoSolicitacao.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }
}
