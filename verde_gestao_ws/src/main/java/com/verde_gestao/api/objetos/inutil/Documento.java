package com.verde_gestao.api.objetos.inutil;

import lombok.Data;

@Data
public class Documento {

    private int documentoId;
    private int tipoDocumentoId;
    private byte[] arquivo;

}
