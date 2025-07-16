package com.verde_gestao.api.controladores;

import com.verde_gestao.api.dto.ResponseCardAviso;
import com.verde_gestao.api.objetos.Aviso;
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
    public ResponseEntity<String> inserirAviso(@RequestBody Aviso aviso) {
        servicoAviso.inserirAviso(aviso);
        return ResponseEntity.ok("Aviso inserido com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarAviso(@PathVariable("id") int avisoId, @RequestBody Aviso aviso) {
        servicoAviso.atualizarAviso(avisoId, aviso);
        return ResponseEntity.ok("Aviso atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAviso(@PathVariable("id") int avisoId) {
        servicoAviso.deletarAviso(avisoId);
        return ResponseEntity.ok("Aviso deletado com sucesso!");
    }

    @GetMapping("/buscarTodosCardsAvisos")
    public List<ResponseCardAviso> buscarTodosCardsAvisos() {
        return servicoAviso.buscarTodosCardsAvisos();
    }

}