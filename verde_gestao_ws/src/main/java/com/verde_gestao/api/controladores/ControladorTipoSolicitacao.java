package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.TipoSolicitacao;
import com.verde_gestao.api.servicos.ServicoTipoSolicitacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoSolicitacaos")
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
    public ResponseEntity<TipoSolicitacao> salvar(@RequestBody TipoSolicitacao objeto) {
        return ResponseEntity.ok(servicoTipoSolicitacao.salvar(objeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoTipoSolicitacao.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }
}
