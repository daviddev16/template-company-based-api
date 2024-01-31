package com.daviddev16.empresa;


import com.daviddev16.empresa.dto.request.RequestCriarEmpresaDTO;
import com.daviddev16.empresa.entity.Empresa;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface EmpresaService {

    List<Empresa> obterTodasEmpresas();

    Empresa obterEmpresaPorId(Authentication authentication, Long empresaId);

    Empresa criarEmpresa(Authentication authentication, RequestCriarEmpresaDTO criarEmpresaDTO);

}
