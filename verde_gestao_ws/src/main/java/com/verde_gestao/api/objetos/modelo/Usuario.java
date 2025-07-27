package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_usuarioid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long usuarioid;

    private boolean administrador;

    private String nome;

    private String senha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipousuarioid")
    private TipoUsuario tipoUsuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "secaoid")
    private Secao secao;

}
