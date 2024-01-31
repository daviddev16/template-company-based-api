package com.daviddev16.empresa.exception;

import com.daviddev16.exception.ServiceException;

import static java.lang.String.format;

public class EmpresaNaoEncontradaException extends ServiceException {

    public EmpresaNaoEncontradaException(Long empresaId) {
        super( format("Não foi possível localizar uma empresa com id '%d'.", empresaId) );
    }

}
