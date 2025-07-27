package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.TipoDocumento;
import com.verde_gestao.api.repositorios.RepositorioTipoDocumento;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoTipoDocumento {

    private final RepositorioTipoDocumento repositorioTipoDocumento;

    public ServicoTipoDocumento(RepositorioTipoDocumento repositorio) {
        this.repositorioTipoDocumento = repositorio;
    }

    public List<TipoDocumento> buscarTodos() {
        return repositorioTipoDocumento.findAll();
    }

    public Optional<TipoDocumento> buscarPorId(Long id) {
        return repositorioTipoDocumento.findById(id);
    }

    public TipoDocumento criar(TipoDocumento tipoDocumento) {
        tipoDocumento.setTipodocumentoid(null);
        return repositorioTipoDocumento.save(tipoDocumento);
    }

    public TipoDocumento atualizar(Long id, TipoDocumento tipoDocumentoAtualizado) {
        TipoDocumento existente = repositorioTipoDocumento.findById(id).orElseThrow(() -> new EntityNotFoundException("Tipo de documento não encontrado com ID: " + id));

        existente.setDescricao(tipoDocumentoAtualizado.getDescricao());
        existente.setInativo(tipoDocumentoAtualizado.isInativo());
        existente.setPrazoEmDias(tipoDocumentoAtualizado.getPrazoEmDias());

        return repositorioTipoDocumento.save(existente);
    }

    public void deletar(Long id) {
        repositorioTipoDocumento.deleteById(id);
    }

}