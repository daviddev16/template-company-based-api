package com.daviddev16.usuario.exception;

import com.daviddev16.exception.ServiceException;

import static java.lang.String.*;

public class UsuarioNaoEncontradoException extends ServiceException {

    public UsuarioNaoEncontradoException(Integer usuarioId) {
        super( format("Não foi possível localizar um usuário com id '%d'.", usuarioId) );
    }

    public UsuarioNaoEncontradoException(String login) {
        super( format("Não foi possível localizar um usuário com login '%s'.", login) );
    }
}
