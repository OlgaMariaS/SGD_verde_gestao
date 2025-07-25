package com.verde_gestao.api.objetos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardAvisoDTO {

    private Long id;
    private String titulo;
    private String texto;
    private String usuario;
    private String data;

}
