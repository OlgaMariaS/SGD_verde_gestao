package com.verde_gestao.api.objetos.dto;

import lombok.Data;

@Data
public class CardAvisoDTO {

    private int id;
    private String titulo;
    private String texto;
    private String usuario;
    private String data;

}