package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.TipoUsuario;
import com.verde_gestao.api.servicos.ServicoTipoUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoUsuarios")
public class ControladorTipoUsuario {

    private final ServicoTipoUsuario servicoTipoUsuario;

    public ControladorTipoUsuario(ServicoTipoUsuario servicoTipoUsuario) {
        this.servicoTipoUsuario = servicoTipoUsuario;
    }

    @GetMapping
    public List<TipoUsuario> buscarTodos() {
        return servicoTipoUsuario.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoTipoUsuario.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> salvar(@RequestBody TipoUsuario objeto) {
        return ResponseEntity.ok(servicoTipoUsuario.salvar(objeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoTipoUsuario.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }
}
