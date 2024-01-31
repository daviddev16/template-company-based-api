package com.daviddev16.usuario.dto;

import lombok.Builder;

@Builder
public record ResponseUsuarioDTO(
        String login, String email,
        String primeiroNome, String segundoNome) { }
