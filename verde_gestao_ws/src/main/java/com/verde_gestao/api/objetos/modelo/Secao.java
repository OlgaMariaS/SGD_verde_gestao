package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "secao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Secao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secao_seq")
    @SequenceGenerator(name = "secao_seq", sequenceName = "secao_secaoid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long secaoid;

    private String descricao;

}
