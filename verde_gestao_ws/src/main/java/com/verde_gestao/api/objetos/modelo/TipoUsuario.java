package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "tipousuario_seq", sequenceName = "tipo_usuario_tipousuarioid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long tipousuarioid;

    private String descricao;

}
