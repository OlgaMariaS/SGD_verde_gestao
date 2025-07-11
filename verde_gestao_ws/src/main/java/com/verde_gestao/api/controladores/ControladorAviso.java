package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.Aviso;
import com.verde_gestao.api.repositorios.RepositorioAviso;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avisos")
public class ControladorAviso {

    private final RepositorioAviso repositorioAviso;

    public ControladorAviso(RepositorioAviso repositorioAviso) {
        this.repositorioAviso = repositorioAviso;
    }

    @GetMapping
    public List<Aviso> getTodosAvisos() {
        return repositorioAviso.buscarTodosAvisos();
    }

    @GetMapping("/{id}")
    public Aviso getAvisoPorId(@PathVariable("id") int avisoId) {
        return repositorioAviso.buscarAvisoPorId(avisoId);
    }

    @PostMapping
    public String inserirAviso(@RequestBody Aviso aviso) {
        int result = repositorioAviso.inserirAviso(aviso);
        return result > 0 ? "Aviso inserido com sucesso!" : "Erro ao inserir aviso.";
    }

    @PutMapping("/{id}")
    public String atualizarAviso(@PathVariable("id") int avisoId, @RequestBody Aviso aviso) {
        aviso.setAvisoId(avisoId);
        int result = repositorioAviso.atualizarAviso(aviso);
        return result > 0 ? "Aviso atualizado com sucesso!" : "Erro ao atualizar aviso.";
    }

    @DeleteMapping("/{id}")
    public String deletarAviso(@PathVariable("id") int avisoId) {
        int result = repositorioAviso.deletarAviso(avisoId);
        return result > 0 ? "Aviso deletado com sucesso!" : "Erro ao deletar aviso.";
    }

}