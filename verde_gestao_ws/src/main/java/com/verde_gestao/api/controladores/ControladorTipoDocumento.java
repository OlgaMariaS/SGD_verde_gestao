package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.TipoDocumento;
import com.verde_gestao.api.servicos.ServicoTipoDocumento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoDocumentos")
public class ControladorTipoDocumento {

    private final ServicoTipoDocumento servicoTipoDocumento;

    public ControladorTipoDocumento(ServicoTipoDocumento servico) {
        this.servicoTipoDocumento = servico;
    }

    @GetMapping
    public List<TipoDocumento> buscarTodos() {
        return servicoTipoDocumento.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<TipoDocumento> criar(@RequestBody TipoDocumento tipoDocumento) {
        return ResponseEntity.ok(servicoTipoDocumento.criar(tipoDocumento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDocumento> atualizar(@PathVariable Long id, @RequestBody TipoDocumento tipoDocumento) {
        return ResponseEntity.ok(servicoTipoDocumento.atualizar(id, tipoDocumento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servicoTipoDocumento.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoTipoDocumento.buscarPorId(id));
    }

}