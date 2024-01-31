package com.daviddev16.usuario.transformer;

import com.daviddev16.usuario.CargoUsuarioEmpresa;
import com.daviddev16.usuario.dto.ResponseCargoUsuarioDTO;
import com.daviddev16.usuario.entity.Usuario;
import com.daviddev16.usuario.dto.ResponseUsuarioDTO;
import com.daviddev16.usuario.entity.UsuarioEmpresa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseUsuarioTransformer {


    public List<ResponseCargoUsuarioDTO> converterListaCargoUsuarioEmResponse(List<UsuarioEmpresa> usuarioEmpresaList) {
        return usuarioEmpresaList.stream()
                .map(this::converterCargoUsuarioEmResponse)
                .collect(Collectors.toList());
    }

    public ResponseCargoUsuarioDTO converterCargoUsuarioEmResponse(UsuarioEmpresa usuarioEmpresa) {
        return ResponseCargoUsuarioDTO
                .builder()
                    .login(usuarioEmpresa.getUsuario().getLogin())
                    .cargo(usuarioEmpresa.getCargoUsuario())
                .build();
    }

    public ResponseUsuarioDTO converterUsuarioEmUsuarioDto(Usuario usuario) {
        return ResponseUsuarioDTO
                .builder()
                    .email(usuario.getEmail())
                    .login(usuario.getLogin())
                    .primeiroNome(usuario.getPrimeiroNome())
                    .segundoNome(usuario.getSegundoNome())
                .build();
    }

    public List<ResponseUsuarioDTO> converterListaUsuarioEmListaUsuarioDto(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::converterUsuarioEmUsuarioDto)
                .collect(Collectors.toList());
    }


}
