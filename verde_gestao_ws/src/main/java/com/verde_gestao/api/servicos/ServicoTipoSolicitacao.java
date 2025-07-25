package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.TipoSolicitacao;
import com.verde_gestao.api.repositorios.RepositorioTipoSolicitacao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoTipoSolicitacao {

    private final RepositorioTipoSolicitacao repositorioTipoSolicitacao;

    public ServicoTipoSolicitacao(RepositorioTipoSolicitacao repositorioTipoSolicitacao) {
        this.repositorioTipoSolicitacao = repositorioTipoSolicitacao;
    }

    public List<TipoSolicitacao> buscarTodos() {
        return repositorioTipoSolicitacao.findAll();
    }

    public Optional<TipoSolicitacao> buscarPorId(Long id) {
        return repositorioTipoSolicitacao.findById(id);
    }

    public TipoSolicitacao salvar(TipoSolicitacao objeto) {
        return repositorioTipoSolicitacao.save(objeto);
    }

    public void excluirPorId(Long id) {
        repositorioTipoSolicitacao.deleteById(id);
    }
}
