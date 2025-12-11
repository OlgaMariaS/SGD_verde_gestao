package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.TipoSolicitacao;
import com.verde_gestao.api.repositorios.RepositorioTipoSolicitacao;
import jakarta.persistence.EntityNotFoundException;
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

    public TipoSolicitacao criar(TipoSolicitacao tipoSolicitacao) {
        tipoSolicitacao.setTiposolicitacaoid(null);
        return repositorioTipoSolicitacao.save(tipoSolicitacao);
    }

    public TipoSolicitacao atualizar(Long id, TipoSolicitacao tipoSolicitacaoAtualizado) {
        TipoSolicitacao existente = repositorioTipoSolicitacao.findById(id).orElseThrow(() -> new EntityNotFoundException("Seção não encontrada com ID: " + id));

        existente.setDescricao(tipoSolicitacaoAtualizado.getDescricao());

        return repositorioTipoSolicitacao.save(existente);
    }

    public void excluirPorId(Long id) {
        repositorioTipoSolicitacao.deleteById(id);
    }
}
