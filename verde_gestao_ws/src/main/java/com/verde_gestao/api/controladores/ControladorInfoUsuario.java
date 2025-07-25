package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.InfoUsuario;
import com.verde_gestao.api.servicos.ServicoInfoUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infoUsuarios")
public class ControladorInfoUsuario {

    private final ServicoInfoUsuario servicoInfoUsuario;

    public ControladorInfoUsuario(ServicoInfoUsuario servicoInfoUsuario) {
        this.servicoInfoUsuario = servicoInfoUsuario;
    }

    @GetMapping
    public List<InfoUsuario> buscarTodos() {
        return servicoInfoUsuario.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoUsuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoInfoUsuario.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<InfoUsuario> salvar(@RequestBody InfoUsuario objeto) {
        return ResponseEntity.ok(servicoInfoUsuario.salvar(objeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoInfoUsuario.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }
}
