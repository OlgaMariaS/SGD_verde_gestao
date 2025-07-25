package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.Secao;
import com.verde_gestao.api.repositorios.RepositorioSecao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoSecao {

    private final RepositorioSecao repositorioSecao;

    public ServicoSecao(RepositorioSecao repositorioSecao) {
        this.repositorioSecao = repositorioSecao;
    }

    public List<Secao> buscarTodos() {
        return repositorioSecao.findAll();
    }

    public Optional<Secao> buscarPorId(Long id) {
        return repositorioSecao.findById(id);
    }

    public Secao salvar(Secao objeto) {
        return repositorioSecao.save(objeto);
    }

    public void excluirPorId(Long id) {
        repositorioSecao.deleteById(id);
    }
}
