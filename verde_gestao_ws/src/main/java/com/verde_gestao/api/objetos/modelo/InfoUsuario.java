package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "info_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class InfoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long infousuarioid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    private String celular;
}
