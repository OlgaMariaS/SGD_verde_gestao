package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_solicitacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class TipoSolicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "tiposolicitacao_seq", sequenceName = "tipo_solicitacao_tiposolicitacaoid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long tiposolicitacaoid;

    private String descricao;

}
