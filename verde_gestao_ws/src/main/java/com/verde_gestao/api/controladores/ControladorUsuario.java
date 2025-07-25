package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.dto.UsuarioLogadoDTO;
import com.verde_gestao.api.objetos.modelo.Usuario;
import com.verde_gestao.api.servicos.ServicoUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {

    private final ServicoUsuario servicoUsuario;

    public ControladorUsuario(ServicoUsuario servicoUsuario) {
        this.servicoUsuario = servicoUsuario;
    }

    @GetMapping
    public List<Usuario> buscarTodosUsuarios() {
        return servicoUsuario.buscarTodosUsuarios();
    }

    @GetMapping("/verificarLogin")
    public ResponseEntity<UsuarioLogadoDTO> verificarLogin(@RequestParam String nome, @RequestParam String senha) {
        return servicoUsuario.verificarLogin(nome, senha);
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(servicoUsuario.criar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(servicoUsuario.atualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoUsuario.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoUsuario.buscarPorId(id));
    }

}
