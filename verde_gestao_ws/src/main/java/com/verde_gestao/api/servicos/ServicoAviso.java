package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.dto.CardAvisoDTO;
import com.verde_gestao.api.objetos.modelo.Aviso;
import com.verde_gestao.api.repositorios.RepositorioAviso;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoAviso {

    private final RepositorioAviso repositorioAviso;

    public ServicoAviso(RepositorioAviso repositorioAviso) {
        this.repositorioAviso = repositorioAviso;
    }

    public List<Aviso> buscarTodos() {
        return repositorioAviso.findAll();
    }

    public Optional<Aviso> buscarPorId(Long id) {
        return repositorioAviso.findById(id);
    }

    public Aviso salvar(Aviso aviso) {
        return repositorioAviso.save(aviso);
    }

    public void excluirPorId(Long id) {
        repositorioAviso.deleteById(id);
    }

    public List<CardAvisoDTO> buscarTodosCardsAvisos() {
        return repositorioAviso.buscarTodosCardsAvisos();
    }
}