package com.daviddev16.empresa.dto.response;

import com.daviddev16.usuario.dto.ResponseCargoUsuarioDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record ResponseEmpresaDTO(Long idEmpresa, String nomeEmpresa,
                                 List<ResponseCargoUsuarioDTO> usuariosAssociados) { }
