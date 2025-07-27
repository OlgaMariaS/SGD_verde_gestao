package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.dto.UsuarioLogadoDTO;
import com.verde_gestao.api.objetos.modelo.Usuario;
import com.verde_gestao.api.repositorios.RepositorioUsuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoUsuario {

    private final RepositorioUsuario repositorioUsuario;

    public ServicoUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public List<Usuario> buscarTodosUsuarios() {
        return repositorioUsuario.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repositorioUsuario.findById(id);
    }

    public Usuario criar(Usuario usuario) {
        usuario.setUsuarioid(null);
        return repositorioUsuario.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario existente = repositorioUsuario.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        existente.setNome(usuarioAtualizado.getNome());
        existente.setSenha(usuarioAtualizado.getSenha());
        existente.setAdministrador(usuarioAtualizado.isAdministrador());
        existente.setSecao(usuarioAtualizado.getSecao());
        existente.setTipoUsuario(usuarioAtualizado.getTipoUsuario());

        return repositorioUsuario.save(existente);
    }

    public void excluirPorId(Long id) {
        repositorioUsuario.deleteById(id);
    }

    public ResponseEntity<UsuarioLogadoDTO> verificarLogin(String nome, String senha) {
        Optional<Usuario> usuarioOpt = repositorioUsuario.findByNomeAndSenha(nome, senha);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            UsuarioLogadoDTO dto = new UsuarioLogadoDTO(
                    usuario.getUsuarioid(),
                    usuario.isAdministrador(),
                    usuario.getNome(),
                    usuario.getTipoUsuario().getDescricao(),
                    usuario.getSecao().getDescricao()
            );
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Usuario> buscarTodosQuemPossoEnviar(Long usuarioId, String tipoUsuarioDescricao) {
        return repositorioUsuario.findByHierarquia(usuarioId, tipoUsuarioDescricao);
    }

}
