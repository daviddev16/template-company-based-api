package com.daviddev16.empresa;

import com.daviddev16.empresa.dto.request.RequestCriarEmpresaDTO;
import com.daviddev16.empresa.dto.response.ResponseEmpresaDTO;
import com.daviddev16.empresa.entity.Empresa;
import com.daviddev16.empresa.transformer.ResponseEmpresaTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/empresa")
public class EmpresaController {

    private final ResponseEmpresaTransformer responseEmpresaTransformer;
    private final EmpresaService empresaService;

    public EmpresaController(ResponseEmpresaTransformer responseEmpresaTransformer,
                             EmpresaService empresaService)
    {
        this.responseEmpresaTransformer = responseEmpresaTransformer;
        this.empresaService = empresaService;
    }

    @GetMapping(value = "/{empresaId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEmpresaDTO obterEmpresaPorId(Authentication authentication,
                                                @PathVariable Long empresaId)
    {
        return responseEmpresaTransformer
                .converterEmpresaEmResponseEmpresaDto( empresaService.obterEmpresaPorId(authentication, empresaId) );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEmpresaDTO criarEmpresa(Authentication authentication,
                                           @RequestBody RequestCriarEmpresaDTO criarEmpresaDTO)
    {
        return responseEmpresaTransformer
                .converterEmpresaEmResponseEmpresaDto( empresaService.criarEmpresa(authentication, criarEmpresaDTO) );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Empresa> obterTodasEmpresas() {
        return empresaService.obterTodasEmpresas();
    }

}
