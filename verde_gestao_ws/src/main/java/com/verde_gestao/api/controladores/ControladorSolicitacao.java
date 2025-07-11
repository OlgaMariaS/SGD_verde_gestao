package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.Solicitacao;
import com.verde_gestao.api.repositorios.RepositorioSolicitacao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class ControladorSolicitacao {

    private final RepositorioSolicitacao repositorioSolicitacao;

    public ControladorSolicitacao(RepositorioSolicitacao repositorioSolicitacao) {
        this.repositorioSolicitacao = repositorioSolicitacao;
    }

    @GetMapping
    public List<Solicitacao> getTodasSolicitacoes() {
        return repositorioSolicitacao.buscarTodasSolicitacoes();
    }

    @GetMapping("/{id}")
    public Solicitacao getSolicitacaoPorId(@PathVariable("id") int solicitacaoId) {
        return repositorioSolicitacao.buscarSolicitacaoPorId(solicitacaoId);
    }

    @GetMapping("/criador/{criadorId}")
    public List<Solicitacao> getSolicitacoesPorCriador(@PathVariable("criadorId") int criadorUsuarioId) {
        return repositorioSolicitacao.buscarSolicitacoesPorCriador(criadorUsuarioId);
    }

    @GetMapping("/responsavel/{responsavelId}")
    public List<Solicitacao> getSolicitacoesPorResponsavel(@PathVariable("responsavelId") int responsavelUsuarioId) {
        return repositorioSolicitacao.buscarSolicitacoesPorResponsavel(responsavelUsuarioId);
    }

    @PostMapping
    public String inserirSolicitacao(@RequestBody Solicitacao solicitacao) {
        int result = repositorioSolicitacao.inserirSolicitacao(solicitacao);
        return result > 0 ? "Solicitação inserida com sucesso!" : "Erro ao inserir solicitação.";
    }

    @PutMapping("/{id}")
    public String atualizarSolicitacao(@PathVariable("id") int solicitacaoId, @RequestBody Solicitacao solicitacao) {
        solicitacao.setSolicitacaoId(solicitacaoId);
        int result = repositorioSolicitacao.atualizarSolicitacao(solicitacao);
        return result > 0 ? "Solicitação atualizada com sucesso!" : "Erro ao atualizar solicitação.";
    }

    @DeleteMapping("/{id}")
    public String deletarSolicitacao(@PathVariable("id") int solicitacaoId) {
        int result = repositorioSolicitacao.deletarSolicitacao(solicitacaoId);
        return result > 0 ? "Solicitação deletada com sucesso!" : "Erro ao deletar solicitação.";
    }

}