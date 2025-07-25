package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"comentarios", "avisos", "logs", "enderecos", "informacoes", "solicitacoesCriadas", "solicitacoesResponsavel"})
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

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aviso> avisos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Log> logs = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoUsuario> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InfoUsuario> informacoes = new ArrayList<>();

    @OneToMany(mappedBy = "criador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solicitacao> solicitacoesCriadas = new ArrayList<>();

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solicitacao> solicitacoesResponsavel = new ArrayList<>();

}
