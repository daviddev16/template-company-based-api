package com.daviddev16.empresa.transformer;

import com.daviddev16.empresa.dto.response.ResponseEmpresaDTO;
import com.daviddev16.empresa.entity.Empresa;
import com.daviddev16.usuario.transformer.ResponseUsuarioTransformer;
import org.springframework.stereotype.Component;

@Component
public class ResponseEmpresaTransformer {

    private final ResponseUsuarioTransformer responseUsuarioTransformer;

    public ResponseEmpresaTransformer(ResponseUsuarioTransformer responseUsuarioTransformer) {
        this.responseUsuarioTransformer = responseUsuarioTransformer;
    }

    public ResponseEmpresaDTO converterEmpresaEmResponseEmpresaDto(Empresa empresa) {
        return ResponseEmpresaDTO
                .builder()
                    .idEmpresa(empresa.getId())
                    .nomeEmpresa(empresa.getNome())
                    .usuariosAssociados( responseUsuarioTransformer
                            .converterListaCargoUsuarioEmResponse(empresa.getUsuariosEmpresa()) )
                .build();
    }

}
