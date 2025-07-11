package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.Usuario;
import com.verde_gestao.api.repositorios.RepositorioUsuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {

    private final RepositorioUsuario repositorioUsuario;

    public ControladorUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @GetMapping
    public List<Usuario> getTodosUsuarios() {
        return repositorioUsuario.buscarTodosUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioPorId(@PathVariable("id") int usuarioId) {
        return repositorioUsuario.buscarUsuarioPorId(usuarioId);
    }

    @GetMapping("/buscarPorNome")
    public Usuario getUsuarioPorNome(@RequestParam String nome) {
        return repositorioUsuario.buscarUsuarioPorNome(nome);
    }

    @GetMapping("/existe")
    public boolean usuarioExiste(@RequestParam String nome) {
        return repositorioUsuario.usuarioExiste(nome);
    }

    @GetMapping("/verificarLogin")
    public boolean verificarLogin(@RequestParam String nome, @RequestParam String senha) {
        return repositorioUsuario.verificarLogin(nome, senha);
    }

    @PostMapping
    public String inserirUsuario(@RequestBody Usuario usuario) {
        int result = repositorioUsuario.inserirUsuario(usuario);
        return result > 0 ? "Usuário inserido com sucesso!" : "Erro ao inserir usuário.";
    }

    @PutMapping("/{id}")
    public String atualizarUsuario(@PathVariable("id") int usuarioId, @RequestBody Usuario usuario) {
        usuario.setUsuarioId(usuarioId);
        int result = repositorioUsuario.atualizarUsuario(usuario);
        return result > 0 ? "Usuário atualizado com sucesso!" : "Erro ao atualizar usuário.";
    }

    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable("id") int usuarioId) {
        int result = repositorioUsuario.deletarUsuario(usuarioId);
        return result > 0 ? "Usuário deletado com sucesso!" : "Erro ao deletar usuário.";
    }

    @GetMapping("/existeAdmin")
    public boolean existeAdministrador() {
        return repositorioUsuario.existeAdministrador();
    }

}