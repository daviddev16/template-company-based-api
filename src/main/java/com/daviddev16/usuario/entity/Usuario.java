package com.daviddev16.usuario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table
public class Usuario {

    @Id
    @SequenceGenerator(
            name = "usuario_idusario_seq",
            sequenceName = "usuario_idusario_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "usuario_idusario_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idusuario",
            nullable = false
    )
    private Long id;


    @Column(
            name = "senha",
            nullable = false
    )
    private String senha;


    @Column(
            name = "login",
            nullable = false
    )
    private String login;


    @Column(
            name = "email",
            nullable = false
    )
    private String email;


    @Column(
            name = "primeironome",
            nullable = false
    )
    private String primeiroNome;


    @Column(
            name = "segundonome",
            nullable = false
    )
    private String segundoNome;


    @Column(
            name = "stativo",
            nullable = false
    )
    private Boolean statusAtivo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioEmpresa> usuarioEmpresas;

    @Transient
    public String getNomeCompleto() {
        return String.format("%s %s", primeiroNome, segundoNome);
    }

}
