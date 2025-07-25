package com.verde_gestao.api.objetos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLogadoDTO {

    private Long usuarioid;
    private boolean administrador;
    private String nome;
    private String tipoUsuario;
    private String secao;

}
