package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.dto.CardAvisoDTO;
import com.verde_gestao.api.objetos.modelo.Aviso;
import com.verde_gestao.api.repositorios.RepositorioAviso;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServicoAviso {

    private final RepositorioAviso repositorioAviso;

    public ServicoAviso(RepositorioAviso repositorioAviso) {
        this.repositorioAviso = repositorioAviso;
    }

    public List<Aviso> buscarTodosAvisos() {
        return repositorioAviso.buscarTodosAvisos();
    }

    public Aviso buscarAvisoPorId(int avisoId) {
        Aviso aviso = repositorioAviso.buscarAvisoPorId(avisoId);

        if (aviso == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviso não encontrado");
        }

        return repositorioAviso.buscarAvisoPorId(avisoId);
    }

    public void inserirAviso(Aviso aviso) {
        repositorioAviso.inserirAviso(aviso);
    }

    public void atualizarAviso(int avisoId, Aviso aviso) {
        aviso.setAvisoId(avisoId);
        repositorioAviso.atualizarAviso(aviso);
    }

    public void deletarAviso(int avisoId) {
        repositorioAviso.deletarAviso(avisoId);
    }

    public List<CardAvisoDTO> buscarTodosCardsAvisos() {
        return repositorioAviso.buscarTodosCardsAvisos();
    }

}
