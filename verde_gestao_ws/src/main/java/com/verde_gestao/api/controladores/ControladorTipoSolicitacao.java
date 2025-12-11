package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.Secao;
import com.verde_gestao.api.objetos.modelo.TipoSolicitacao;
import com.verde_gestao.api.servicos.ServicoTipoSolicitacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoSolicitacoes")
public class ControladorTipoSolicitacao {

    private final ServicoTipoSolicitacao servicoTipoSolicitacao;

    public ControladorTipoSolicitacao(ServicoTipoSolicitacao servicoTipoSolicitacao) {
        this.servicoTipoSolicitacao = servicoTipoSolicitacao;
    }

    @GetMapping
    public List<TipoSolicitacao> buscarTodos() {
        return servicoTipoSolicitacao.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSolicitacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoTipoSolicitacao.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoSolicitacao> criar(@RequestBody TipoSolicitacao tipoSolicitacao) {
        return ResponseEntity.ok(servicoTipoSolicitacao.criar(tipoSolicitacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoSolicitacao> atualizar(@PathVariable Long id, @RequestBody TipoSolicitacao tipoSolicitacao) {
        return ResponseEntity.ok(servicoTipoSolicitacao.atualizar(id, tipoSolicitacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoTipoSolicitacao.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

}
