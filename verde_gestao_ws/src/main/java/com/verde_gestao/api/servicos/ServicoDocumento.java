package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.Documento;
import com.verde_gestao.api.repositorios.RepositorioDocumento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoDocumento {

    private final RepositorioDocumento repositorioDocumento;

    public ServicoDocumento(RepositorioDocumento repositorioDocumento) {
        this.repositorioDocumento = repositorioDocumento;
    }

    public List<Documento> buscarTodos() {
        return repositorioDocumento.findAll();
    }

    public Optional<Documento> buscarPorId(Long id) {
        return repositorioDocumento.findById(id);
    }

    public Documento salvar(Documento objeto) {
        return repositorioDocumento.save(objeto);
    }

    public void excluirPorId(Long id) {
        repositorioDocumento.deleteById(id);
    }

}
