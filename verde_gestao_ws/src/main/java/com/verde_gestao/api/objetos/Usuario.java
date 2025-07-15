package com.verde_gestao.api.objetos;

import lombok.Data;

@Data
public class Usuario {

    private int usuarioId;
    private boolean administrador;
    private String nome;
    private String senha;
    private int tipoUsuarioId;
    private int secaoId;

}
