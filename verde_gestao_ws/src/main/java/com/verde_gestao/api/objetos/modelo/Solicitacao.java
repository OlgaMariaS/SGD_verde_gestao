package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "solicitacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_seq")
    @SequenceGenerator(name = "solicitacao_seq", sequenceName = "solicitacao_solicitacaoid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long solicitacaoid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tiposolicitacaoid")
    private TipoSolicitacao tipoSolicitacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "criador_usuarioid")
    private Usuario criador;

    @ManyToOne(optional = false)
    @JoinColumn(name = "responsavel_usuarioid")
    private Usuario responsavel;

    private LocalDate dataCriacao;

    private String status;

    private String descricao;

}
