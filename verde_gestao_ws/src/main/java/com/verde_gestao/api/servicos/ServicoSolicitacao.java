package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.Solicitacao;
import com.verde_gestao.api.objetos.modelo.Usuario;
import com.verde_gestao.api.repositorios.RepositorioSolicitacao;
import jakarta.persistence.EntityNotFoundException;
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

    public Solicitacao criar(Solicitacao solicitacao) {
        solicitacao.setSolicitacaoid(null);
        return repositorioSolicitacao.save(solicitacao);
    }

    public Solicitacao atualizar(Long id, Solicitacao solicitacaoAtualizada) {
        Solicitacao existente = repositorioSolicitacao.findById(id).orElseThrow(() -> new EntityNotFoundException("Solicitação não encontrada com ID: " + id));

        existente.setDescricao(solicitacaoAtualizada.getDescricao());
        existente.setCriador(solicitacaoAtualizada.getCriador());
        existente.setComentarios(solicitacaoAtualizada.getComentarios());
        existente.setStatus(solicitacaoAtualizada.getStatus());
        existente.setResponsavel(solicitacaoAtualizada.getResponsavel());
        existente.setDataCriacao(solicitacaoAtualizada.getDataCriacao());
        existente.setTipoSolicitacao(solicitacaoAtualizada.getTipoSolicitacao());

        return repositorioSolicitacao.save(existente);
    }

    public void excluirPorId(Long id) {
        repositorioSolicitacao.deleteById(id);
    }

    public List<Solicitacao> buscarMinhasSolicitacoes(Long usuarioId) {
        return repositorioSolicitacao.findByResponsavelAndCriador(usuarioId);
    }
}
