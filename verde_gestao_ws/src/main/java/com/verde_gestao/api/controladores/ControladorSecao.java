package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.Secao;
import com.verde_gestao.api.repositorios.RepositorioSecao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secoes")
public class ControladorSecao {

    private final RepositorioSecao repositorioSecao;

    public ControladorSecao(RepositorioSecao repositorioSecao) {
        this.repositorioSecao = repositorioSecao;
    }

    @GetMapping
    public List<Secao> buscarTodasSecoes() {
        return repositorioSecao.buscarTodasSecoes();
    }

    @GetMapping("/{id}")
    public Secao buscarSecaoPorId(@PathVariable("id") int secaoId) {
        return repositorioSecao.buscarSecaoPorId(secaoId);
    }

    @GetMapping("/buscarPorDescricao")
    public Secao buscarSecaoPorDescricao(@RequestParam String descricao) {
        return repositorioSecao.buscarSecaoPorDescricao(descricao);
    }

    @GetMapping("/existe")
    public boolean secaoExiste(@RequestParam String descricao) {
        return repositorioSecao.secaoExiste(descricao);
    }

    @PostMapping
    public String inserirSecao(@RequestBody Secao secao) {
        int result = repositorioSecao.inserirSecao(secao);
        return result > 0 ? "Seção inserida com sucesso!" : "Erro ao inserir seção.";
    }

    @PutMapping("/{id}")
    public String atualizarSecao(@PathVariable("id") int secaoId, @RequestBody Secao secao) {
        secao.setSecaoid(secaoId);
        int result = repositorioSecao.atualizarSecao(secao);
        return result > 0 ? "Seção atualizada com sucesso!" : "Erro ao atualizar seção.";
    }

    @DeleteMapping("/{id}")
    public String deletarSecao(@PathVariable("id") int secaoId) {
        int result = repositorioSecao.deletarSecao(secaoId);
        return result > 0 ? "Seção deletada com sucesso!" : "Erro ao deletar seção.";
    }

}