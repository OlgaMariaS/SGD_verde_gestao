package com.verde_gestao.api.objetos.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "secao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Secao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secao_seq")
    @SequenceGenerator(name = "secao_seq", sequenceName = "secao_secaoid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long secaoid;

    private String descricao;

    // Coloquei aqui para deletar todos os usuários ao ser realizada a deleção de uma seção.
    @JsonIgnore
    @OneToMany(mappedBy = "secao", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Usuario> usuarios = new ArrayList<>();

}
