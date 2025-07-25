package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.Log;
import com.verde_gestao.api.servicos.ServicoLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class ControladorLog {

    private final ServicoLog servicoLog;

    public ControladorLog(ServicoLog servicoLog) {
        this.servicoLog = servicoLog;
    }

    @GetMapping
    public List<Log> buscarTodos() {
        return servicoLog.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoLog.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Log> salvar(@RequestBody Log objeto) {
        return ResponseEntity.ok(servicoLog.salvar(objeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoLog.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }
}
