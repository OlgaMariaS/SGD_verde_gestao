package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.EnderecoUsuario;
import com.verde_gestao.api.servicos.ServicoEnderecoUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecoUsuarios")
public class ControladorEnderecoUsuario {

    private final ServicoEnderecoUsuario servicoEnderecoUsuario;

    public ControladorEnderecoUsuario(ServicoEnderecoUsuario servicoEnderecoUsuario) {
        this.servicoEnderecoUsuario = servicoEnderecoUsuario;
    }

    @GetMapping
    public List<EnderecoUsuario> buscarTodos() {
        return servicoEnderecoUsuario.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoUsuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoEnderecoUsuario.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EnderecoUsuario> salvar(@RequestBody EnderecoUsuario objeto) {
        return ResponseEntity.ok(servicoEnderecoUsuario.salvar(objeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoEnderecoUsuario.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

}
