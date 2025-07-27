package com.verde_gestao.api.objetos.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "log_seq", sequenceName = "log_logid_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long logid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    private String info;

}
