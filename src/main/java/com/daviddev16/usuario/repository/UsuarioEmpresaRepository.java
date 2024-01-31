package com.daviddev16.usuario.repository;

import com.daviddev16.usuario.entity.UsuarioEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioEmpresaRepository extends JpaRepository<UsuarioEmpresa, Long> {

    /* não usar query methods */
    @Query(
            nativeQuery = true,
            value = " SELECT true FROM empcargousuarios WHERE idusuario = :paramUsuarioId AND idempresa = :paramEmpresaId LIMIT 1"
    )
    Optional<Boolean> verificarOwnership(@Param("paramUsuarioId") Long usuarioId, @Param("paramEmpresaId") Long empresaId );

}
