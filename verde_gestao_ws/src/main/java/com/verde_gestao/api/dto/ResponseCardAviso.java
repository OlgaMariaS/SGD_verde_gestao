package com.verde_gestao.api.dto;

import lombok.Data;

@Data
public class ResponseCardAviso {

    private int id;
    private String tituloAviso;
    private String mensagem;
    private String usuario;
    private String dataHora;

}