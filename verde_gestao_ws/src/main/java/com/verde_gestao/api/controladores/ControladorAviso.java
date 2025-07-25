package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.dto.CardAvisoDTO;
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
    public List<Aviso> buscarTodos() {
        return servicoAviso.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aviso> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoAviso.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Aviso> salvar(@RequestBody Aviso aviso) {
        return ResponseEntity.ok(servicoAviso.salvar(aviso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoAviso.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cards")
    public List<CardAvisoDTO> buscarTodosCardsAvisos() {
        return servicoAviso.buscarTodosCardsAvisos();
    }

}