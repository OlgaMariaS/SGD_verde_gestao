package com.verde_gestao.api.objetos.modelo;

import lombok.Data;

@Data
public class Aviso {

    private int avisoId;
    private int autorUsuarioId;
    private String titulo;
    private String texto;
    private String dataInicio;
    private String dataFim;

}
