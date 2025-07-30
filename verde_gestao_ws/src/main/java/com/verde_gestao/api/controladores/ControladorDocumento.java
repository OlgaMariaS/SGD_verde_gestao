package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.Documento;
import com.verde_gestao.api.servicos.ServicoDocumento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/documentos")
public class ControladorDocumento {

    private final ServicoDocumento servicoDocumento;

    public ControladorDocumento(ServicoDocumento servicoDocumento) {
        this.servicoDocumento = servicoDocumento;
    }

    @GetMapping
    public List<Documento> buscarTodos() {
        return servicoDocumento.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(servicoDocumento.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Documento> salvar(@RequestBody Documento objeto) {
        byte[] conteudo = Base64.getDecoder().decode(objeto.getArquivo());
        objeto.setArquivo(conteudo);

        System.out.println(Arrays.toString(objeto.getArquivo()));

        return ResponseEntity.ok(servicoDocumento.salvar(objeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        servicoDocumento.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

}
