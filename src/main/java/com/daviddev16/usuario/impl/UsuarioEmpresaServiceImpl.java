package com.daviddev16.usuario.impl;

import com.daviddev16.empresa.entity.Empresa;
import com.daviddev16.usuario.CargoUsuarioEmpresa;
import com.daviddev16.usuario.entity.Usuario;
import com.daviddev16.usuario.entity.UsuarioEmpresa;
import com.daviddev16.usuario.repository.UsuarioEmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEmpresaServiceImpl implements UsuarioEmpresaService {

    private final UsuarioEmpresaRepository usuarioEmpresaRepository;

    public UsuarioEmpresaServiceImpl(UsuarioEmpresaRepository usuarioEmpresaRepository) {
        this.usuarioEmpresaRepository = usuarioEmpresaRepository;
    }

    @Override
    public UsuarioEmpresa criarUsuarioEmpresa(Usuario usuario, Empresa empresa, CargoUsuarioEmpresa cargoUsuario) {

        UsuarioEmpresa novoUsuarioEmpresa = UsuarioEmpresa
                .builder()
                    .cargoUsuario(cargoUsuario)
                    .empresa(empresa)
                    .usuario(usuario)
                .build();

        return usuarioEmpresaRepository.save(novoUsuarioEmpresa);

    }

    @Override
    public boolean verificarOwnership(Long usuarioId, Long empresaId) {
        return usuarioEmpresaRepository
                .verificarOwnership(usuarioId, empresaId)
                .isPresent();
    }
}
