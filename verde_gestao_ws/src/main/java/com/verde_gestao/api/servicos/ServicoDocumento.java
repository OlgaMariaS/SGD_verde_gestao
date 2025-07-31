package com.verde_gestao.api.servicos;

import com.verde_gestao.api.objetos.modelo.Documento;
import com.verde_gestao.api.objetos.modelo.Solicitacao;
import com.verde_gestao.api.objetos.modelo.TipoDocumento;
import com.verde_gestao.api.repositorios.RepositorioDocumento;
import com.verde_gestao.api.repositorios.RepositorioSolicitacao;
import com.verde_gestao.api.repositorios.RepositorioTipoDocumento;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoDocumento {

    private final RepositorioDocumento repositorioDocumento;
    private final RepositorioTipoDocumento repositorioTipoDocumento;
    private final RepositorioSolicitacao repositorioSolicitacao;

    public ServicoDocumento(RepositorioDocumento repositorioDocumento, RepositorioTipoDocumento repositorioTipoDocumento, RepositorioSolicitacao repositorioSolicitacao) {
        this.repositorioDocumento = repositorioDocumento;
        this.repositorioTipoDocumento = repositorioTipoDocumento;
        this.repositorioSolicitacao = repositorioSolicitacao;
    }

    public List<Documento> buscarTodos() {
        return repositorioDocumento.findAll();
    }

    public Optional<Documento> buscarPorId(Long id) {
        return repositorioDocumento.findById(id);
    }

    public Documento salvar(Documento objeto) {
        return repositorioDocumento.save(objeto);
    }

    public void excluirPorId(Long id) {
        repositorioDocumento.deleteById(id);
    }

    public ResponseEntity<byte[]> download(Long id) {
        Documento doc = repositorioDocumento.findById(id).orElseThrow();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getDocumentoid() + "\"")
                .body(doc.getArquivo());
    }

    public ResponseEntity<Void> upload(
            MultipartFile file,
            Long tipoDocumentoId,
            Long solicitacaoId) throws IOException {

        TipoDocumento tipo = repositorioTipoDocumento.findById(tipoDocumentoId).orElseThrow();
        Solicitacao solicitacao = repositorioSolicitacao.findById(solicitacaoId).orElseThrow();

        Documento doc = new Documento();
        doc.setTipodocumento(tipo);
        doc.setSolicitacao(solicitacao);
        doc.setArquivo(file.getBytes());
        repositorioDocumento.save(doc);

        return ResponseEntity.ok().build();
    }

}
