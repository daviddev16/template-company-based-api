package com.daviddev16.usuario.entity;


import com.daviddev16.empresa.entity.Empresa;
import com.daviddev16.usuario.CargoUsuarioEmpresa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "empcargousuarios",
        indexes = {
                @Index(name = "idx_empcargousuarios_idusuarioempresa",
                        columnList = "idempresa, idusuario")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_empcargousuarios_cargounico",
                        columnNames = {"idempresa", "idusuario", "nmcargousuario"})
        }
)
public class UsuarioEmpresa {

    @Id
    @SequenceGenerator(
            name = "empcargousuarios_idusuarioempresa_seq",
            sequenceName = "empcargousuarios_idusuarioempresa_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "empcargousuarios_idusuarioempresa_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idusuarioempresa",
            nullable = false
    )
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "idempresa",
            foreignKey = @ForeignKey(name = "empcargousuarios_idempresa_fk"),
            nullable = false

    )
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(
            name = "idusuario",
            foreignKey = @ForeignKey(name = "empcargousuarios_idusuario_fk"),
            nullable = false
    )
    private Usuario usuario;


    @Column(
            name = "nmcargousuario",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private CargoUsuarioEmpresa cargoUsuario;

}
