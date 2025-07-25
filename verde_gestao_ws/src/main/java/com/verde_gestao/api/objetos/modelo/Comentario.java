package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comentario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentario_seq")
    @SequenceGenerator(name = "comentario_seq", sequenceName = "comentario_comentarioid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long comentarioid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "solicitacaoid")
    private Solicitacao solicitacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "autor_usuarioid")
    private Usuario autor;

    private String texto;

}
