package com.verde_gestao.api.objetos;

public class Solicitacao {
    private int solicitacaoId;
    private int tipoSolicitacaoId;
    private int criadorUsuarioId;
    private int responsavelUsuarioId;
    private String dataCriacao;
    private String status;
    private String descricao;

    public int getSolicitacaoId() {
        return solicitacaoId;
    }

    public void setSolicitacaoId(int solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    public int getTipoSolicitacaoId() {
        return tipoSolicitacaoId;
    }

    public void setTipoSolicitacaoId(int tipoSolicitacaoId) {
        this.tipoSolicitacaoId = tipoSolicitacaoId;
    }

    public int getCriadorUsuarioId() {
        return criadorUsuarioId;
    }

    public void setCriadorUsuarioId(int criadorUsuarioId) {
        this.criadorUsuarioId = criadorUsuarioId;
    }

    public int getResponsavelUsuarioId() {
        return responsavelUsuarioId;
    }

    public void setResponsavelUsuarioId(int responsavelUsuarioId) {
        this.responsavelUsuarioId = responsavelUsuarioId;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
