package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_documento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_documento_seq")
    @SequenceGenerator(name = "tipo_documento_seq", sequenceName = "tipo_documento_tipodocumentoid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long tipodocumentoid;

    private String descricao;

    private Long prazoEmDias;

    private boolean inativo;

}