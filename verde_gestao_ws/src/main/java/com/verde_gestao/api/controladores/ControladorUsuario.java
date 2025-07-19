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

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable("id") int id) {
        return servicoUsuario.buscarUsuarioPorId(id);
    }

    @GetMapping("/buscarPorNome")
    public Usuario buscarUsuarioPorNome(@RequestParam String nome) {
        return servicoUsuario.buscarUsuarioPorNome(nome);
    }

    @GetMapping("/existe")
    public boolean usuarioExiste(@RequestParam String nome) {
        return servicoUsuario.usuarioExiste(nome);
    }

    @GetMapping("/verificarLogin")
    public ResponseEntity<UsuarioLogadoDTO> verificarLogin(@RequestParam String nome, @RequestParam String senha) {
        return servicoUsuario.verificarLogin(nome, senha);
    }

    @PostMapping
    public ResponseEntity<String> inserirUsuario(@RequestBody Usuario usuario) {
        servicoUsuario.inserirUsuario(usuario);
        return ResponseEntity.ok("Usuário inserido com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        servicoUsuario.atualizarUsuario(id, usuario);
        return ResponseEntity.ok("Usuário atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable("id") int id) {
        servicoUsuario.deletarUsuario(id);
        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }

    @GetMapping("/existeAdmin")
    public boolean existeAdministrador() {
        return servicoUsuario.existeAdministrador();
    }

}
