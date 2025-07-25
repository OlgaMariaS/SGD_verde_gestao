package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.Secao;
import com.verde_gestao.api.servicos.ServicoSecao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secoes")
public class ControladorSecao {

    private final ServicoSecao servicoSecao;

    public ControladorSecao(ServicoSecao servicoSecao) {
        this.servicoSecao = servicoSecao;
    }

    @GetMapping
    public List<Secao> buscarTodos() {
        return servicoSecao.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Secao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoSecao.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Secao> salvar(@RequestBody Secao objeto) {
        return ResponseEntity.ok(servicoSecao.salvar(objeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoSecao.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }
}
