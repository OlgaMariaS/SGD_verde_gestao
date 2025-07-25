package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.TipoUsuario;
import com.verde_gestao.api.repositorios.RepositorioTipoUsuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoTipoUsuario {

    private final RepositorioTipoUsuario repositorioTipoUsuario;

    public ServicoTipoUsuario(RepositorioTipoUsuario repositorioTipoUsuario) {
        this.repositorioTipoUsuario = repositorioTipoUsuario;
    }

    public List<TipoUsuario> buscarTodos() {
        return repositorioTipoUsuario.findAll();
    }

    public Optional<TipoUsuario> buscarPorId(Long id) {
        return repositorioTipoUsuario.findById(id);
    }

    public TipoUsuario salvar(TipoUsuario objeto) {
        return repositorioTipoUsuario.save(objeto);
    }

    public void excluirPorId(Long id) {
        repositorioTipoUsuario.deleteById(id);
    }
}
