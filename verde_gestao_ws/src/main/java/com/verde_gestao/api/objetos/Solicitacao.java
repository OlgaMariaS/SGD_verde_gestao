package com.verde_gestao.api.objetos;

import lombok.Data;

@Data
public class Solicitacao {

    private int solicitacaoId;
    private int tipoSolicitacaoId;
    private int criadorUsuarioId;
    private int responsavelUsuarioId;
    private String dataCriacao;
    private String status;
    private String descricao;

}
