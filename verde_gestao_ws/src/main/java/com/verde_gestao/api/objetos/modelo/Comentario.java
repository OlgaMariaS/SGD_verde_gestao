package com.verde_gestao.api.objetos.modelo;

import lombok.Data;

@Data
public class Comentario {

    private int comentarioId;
    private int solicitacaoId;
    private int autorUsuarioId;
    private String texto;

}
