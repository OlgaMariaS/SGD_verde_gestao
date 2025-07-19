package com.verde_gestao.api.objetos.dto;

import lombok.Data;

@Data
public class UsuarioLogadoDTO {

    private int usuarioId;
    private boolean administrador;
    private String nome;
    private String tipoUsuario;
    private String secao;

}
