package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.dto.ResponseCardAviso;
import com.verde_gestao.api.objetos.dto.ResponseMensagemSimples;
import com.verde_gestao.api.objetos.modelo.Aviso;
import com.verde_gestao.api.servicos.ServicoAviso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avisos")
public class ControladorAviso {

    private final ServicoAviso servicoAviso;

    public ControladorAviso(ServicoAviso servicoAviso) {
        this.servicoAviso = servicoAviso;
    }

    @GetMapping
    public List<Aviso> buscarTodosAvisos() {
        return servicoAviso.buscarTodosAvisos();
    }

    @GetMapping("/{id}")
    public Aviso buscarAvisoPorId(@PathVariable("id") int avisoId) {
        return servicoAviso.buscarAvisoPorId(avisoId);
    }

    @PostMapping
    public ResponseEntity<ResponseMensagemSimples> inserirAviso(@RequestBody Aviso aviso) {
        servicoAviso.inserirAviso(aviso);
        return ResponseEntity.ok(new ResponseMensagemSimples("Aviso inserido com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMensagemSimples> atualizarAviso(@PathVariable("id") int avisoId, @RequestBody Aviso aviso) {
        servicoAviso.atualizarAviso(avisoId, aviso);
        return ResponseEntity.ok(new ResponseMensagemSimples("Aviso atualizado com sucesso!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMensagemSimples> deletarAviso(@PathVariable("id") int avisoId) {
        servicoAviso.deletarAviso(avisoId);
        return ResponseEntity.ok(new ResponseMensagemSimples("Aviso deletado com sucesso!"));
    }

    @GetMapping("/buscarTodosCardsAvisos")
    public List<ResponseCardAviso> buscarTodosCardsAvisos() {
        return servicoAviso.buscarTodosCardsAvisos();
    }

}