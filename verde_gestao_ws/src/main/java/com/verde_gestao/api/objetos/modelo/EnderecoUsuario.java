package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "endereco_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class EnderecoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @SequenceGenerator(name = "enderecousuarioid_seq", sequenceName = "endereco_usuario_enderecousuarioid_seq", allocationSize = 1)
    private Long enderecousuarioid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    private String numero;

    private String logradouro;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

}
