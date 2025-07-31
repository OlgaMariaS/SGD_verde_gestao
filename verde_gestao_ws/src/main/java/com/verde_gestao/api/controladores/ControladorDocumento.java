package com.verde_gestao.api.controladores;

import com.verde_gestao.api.objetos.modelo.Documento;
import com.verde_gestao.api.servicos.ServicoDocumento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {
        return servicoDocumento.download(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> upload(
            @RequestParam("arquivo") MultipartFile file,
            @RequestParam("tipodocumentoid") Long tipoDocumentoId,
            @RequestParam("solicitacaoid") Long solicitacaoId) throws IOException {

        System.out.println("tipodocumentoid: " + tipoDocumentoId);
        System.out.println("solicitacaoid: " + solicitacaoId);
        System.out.println("arquivo nulo? " + (file == null));
        System.out.println("arquivo vazio? " + file.isEmpty());

        return servicoDocumento.upload(file, tipoDocumentoId, solicitacaoId);
    }


}
