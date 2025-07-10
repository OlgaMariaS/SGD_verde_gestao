package com.verde_gestao.api.objetos;

public class Aviso {
    private int avisoId;
    private int autorUsuarioId;
    private String texto;
    private String dataInicio;
    private String dataFim;

    public int getAvisoId() {
        return avisoId;
    }

    public void setAvisoId(int avisoId) {
        this.avisoId = avisoId;
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

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}
