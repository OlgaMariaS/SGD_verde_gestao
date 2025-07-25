package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.Solicitacao;
import com.verde_gestao.api.repositorios.RepositorioSolicitacao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoSolicitacao {

    private final RepositorioSolicitacao repositorioSolicitacao;

    public ServicoSolicitacao(RepositorioSolicitacao repositorioSolicitacao) {
        this.repositorioSolicitacao = repositorioSolicitacao;
    }

    public List<Solicitacao> buscarTodos() {
        return repositorioSolicitacao.findAll();
    }

    public Optional<Solicitacao> buscarPorId(Long id) {
        return repositorioSolicitacao.findById(id);
    }

    public Solicitacao salvar(Solicitacao objeto) {
        return repositorioSolicitacao.save(objeto);
    }

    public void excluirPorId(Long id) {
        repositorioSolicitacao.deleteById(id);
    }
}
