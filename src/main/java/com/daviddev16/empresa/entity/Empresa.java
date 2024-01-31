package com.daviddev16.empresa.entity;

import com.daviddev16.usuario.entity.UsuarioEmpresa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @SequenceGenerator(
            name = "empresa_idempresa_seq",
            sequenceName = "empresa_idempresa_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "empresa_idempresa_seq"
    )
    @Column(
            name = "idempresa",
            nullable = false
    )
    private Long id;


    @Column(
            name = "nmempresa",
            nullable = false
    )
    private String nome;


    @OneToMany(mappedBy = "empresa")
    private List<UsuarioEmpresa> usuariosEmpresa;

}
