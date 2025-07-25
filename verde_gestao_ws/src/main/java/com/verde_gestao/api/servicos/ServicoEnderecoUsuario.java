package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.EnderecoUsuario;
import com.verde_gestao.api.repositorios.RepositorioEnderecoUsuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoEnderecoUsuario {

    private final RepositorioEnderecoUsuario repositorioEnderecoUsuario;

    public ServicoEnderecoUsuario(RepositorioEnderecoUsuario repositorioEnderecoUsuario) {
        this.repositorioEnderecoUsuario = repositorioEnderecoUsuario;
    }

    public List<EnderecoUsuario> buscarTodos() {
        return repositorioEnderecoUsuario.findAll();
    }

    public Optional<EnderecoUsuario> buscarPorId(Long id) {
        return repositorioEnderecoUsuario.findById(id);
    }

    public EnderecoUsuario salvar(EnderecoUsuario objeto) {
        return repositorioEnderecoUsuario.save(objeto);
    }

    public void excluirPorId(Long id) {
        repositorioEnderecoUsuario.deleteById(id);
    }
}
