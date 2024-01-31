package com.daviddev16.usuario.dto;

import com.daviddev16.usuario.CargoUsuarioEmpresa;
import lombok.Builder;

@Builder
public record ResponseCargoUsuarioDTO(String login, CargoUsuarioEmpresa cargo) { }
