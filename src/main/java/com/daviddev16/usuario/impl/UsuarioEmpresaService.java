package com.daviddev16.usuario.impl;

import com.daviddev16.empresa.entity.Empresa;
import com.daviddev16.usuario.CargoUsuarioEmpresa;
import com.daviddev16.usuario.entity.Usuario;
import com.daviddev16.usuario.entity.UsuarioEmpresa;

public interface UsuarioEmpresaService {

    UsuarioEmpresa criarUsuarioEmpresa(Usuario usuario, Empresa empresa, CargoUsuarioEmpresa cargoUsuarioEmpresa);

    boolean verificarOwnership(Long usuarioId, Long empresaId );

}
