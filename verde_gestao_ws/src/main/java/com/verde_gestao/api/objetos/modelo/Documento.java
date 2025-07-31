package com.verde_gestao.api.objetos.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documento_seq")
    @SequenceGenerator(name = "documento_seq", sequenceName = "documento_documentoid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long documentoid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipodocumentoid")
    private TipoDocumento tipodocumento;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "solicitacaoid")
    private Solicitacao solicitacao;

    @JsonIgnore
    @Column(name = "arquivo", columnDefinition = "bytea")
    private byte[] arquivo;

}
