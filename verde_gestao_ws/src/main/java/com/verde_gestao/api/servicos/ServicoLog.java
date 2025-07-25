package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.Log;
import com.verde_gestao.api.repositorios.RepositorioLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoLog {

    private final RepositorioLog repositorioLog;

    public ServicoLog(RepositorioLog repositorioLog) {
        this.repositorioLog = repositorioLog;
    }

    public List<Log> buscarTodos() {
        return repositorioLog.findAll();
    }

    public Optional<Log> buscarPorId(Long id) {
        return repositorioLog.findById(id);
    }

    public Log salvar(Log objeto) {
        return repositorioLog.save(objeto);
    }

    public void excluirPorId(Long id) {
        repositorioLog.deleteById(id);
    }
}
