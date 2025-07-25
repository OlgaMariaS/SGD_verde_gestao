package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.InfoUsuario;
import com.verde_gestao.api.repositorios.RepositorioInfoUsuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoInfoUsuario {

    private final RepositorioInfoUsuario repositorioInfoUsuario;

    public ServicoInfoUsuario(RepositorioInfoUsuario repositorioInfoUsuario) {
        this.repositorioInfoUsuario = repositorioInfoUsuario;
    }

    public List<InfoUsuario> buscarTodos() {
        return repositorioInfoUsuario.findAll();
    }

    public Optional<InfoUsuario> buscarPorId(Long id) {
        return repositorioInfoUsuario.findById(id);
    }

    public InfoUsuario salvar(InfoUsuario objeto) {
        return repositorioInfoUsuario.save(objeto);
    }

    public void excluirPorId(Long id) {
        repositorioInfoUsuario.deleteById(id);
    }
}
