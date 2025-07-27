package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "solicitacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"comentarios"})
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_seq")
    @SequenceGenerator(name = "solicitacao_seq", sequenceName = "solicitacao_solicitacaoid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long solicitacaoid;

    @ManyToOne
    @JoinColumn(name = "tiposolicitacaoid", nullable = false)
    private TipoSolicitacao tipoSolicitacao;

    @ManyToOne
    @JoinColumn(name = "criador_usuarioid", nullable = false)
    private Usuario criador;

    @ManyToOne
    @JoinColumn(name = "responsavel_usuarioid", nullable = false)
    private Usuario responsavel;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "solicitacao")
    private List<Comentario> comentarios;

}
