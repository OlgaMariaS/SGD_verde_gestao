package com.verde_gestao.api.objetos;

public class Comentario {
    private int comentarioId;
    private int solicitacaoId;
    private int autorUsuarioId;
    private String texto;

    public int getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(int comentarioId) {
        this.comentarioId = comentarioId;
    }

    public int getSolicitacaoId() {
        return solicitacaoId;
    }

    public void setSolicitacaoId(int solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    public int getAutorUsuarioId() {
        return autorUsuarioId;
    }

    public void setAutorUsuarioId(int autorUsuarioId) {
        this.autorUsuarioId = autorUsuarioId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
