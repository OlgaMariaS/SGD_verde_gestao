package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "aviso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aviso_seq")
    @SequenceGenerator(name = "aviso_seq", sequenceName = "aviso_avisoid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long avisoid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "autor_usuarioid")
    private Usuario autor;

    private String titulo;

    private String texto;

    private LocalDate dataInicio;

    private LocalDate dataFim;

}
