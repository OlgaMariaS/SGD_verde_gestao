package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.dto.UsuarioLogadoDTO;
import com.verde_gestao.api.objetos.modelo.Usuario;
import com.verde_gestao.api.repositorios.RepositorioUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServicoUsuario {

    private final RepositorioUsuario repositorioUsuario;

    public ServicoUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public List<Usuario> buscarTodosUsuarios() {
        return repositorioUsuario.buscarTodosUsuarios();
    }

    public Usuario buscarUsuarioPorId(int usuarioId) {
        Usuario usuario = repositorioUsuario.buscarUsuarioPorId(usuarioId);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return usuario;
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        Usuario usuario = repositorioUsuario.buscarUsuarioPorNome(nome);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return usuario;
    }

    public boolean usuarioExiste(String nome) {
        return repositorioUsuario.usuarioExiste(nome);
    }

    public ResponseEntity<UsuarioLogadoDTO> verificarLogin(String nome, String senha) {
        boolean loginSucedido = repositorioUsuario.verificarLogin(nome, senha);

        if (!loginSucedido) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        UsuarioLogadoDTO usuarioLogado = repositorioUsuario.buscarUsuarioLogado(nome, senha);
        return ResponseEntity.ok(usuarioLogado);
    }

    public void inserirUsuario(Usuario usuario) {
        if (usuarioExiste(usuario.getNome())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já existe");
        }
        int res = repositorioUsuario.inserirUsuario(usuario);
        if (res <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao inserir usuário");
        }
    }

    public void atualizarUsuario(int usuarioId, Usuario usuario) {
        usuario.setUsuarioId(usuarioId);
        int res = repositorioUsuario.atualizarUsuario(usuario);
        if (res <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao atualizar usuário ou usuário não encontrado");
        }
    }

    public void deletarUsuario(int usuarioId) {
        int res = repositorioUsuario.deletarUsuario(usuarioId);
        if (res <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao deletar usuário ou usuário não encontrado");
        }
    }

    public boolean existeAdministrador() {
        return repositorioUsuario.existeAdministrador();
    }

}
