package com.verde_gestao.api.objetos;

import lombok.Data;

@Data
public class Aviso {

    private int avisoId;
    private int autorUsuarioId;
    private String texto;
    private String dataInicio;
    private String dataFim;

}
