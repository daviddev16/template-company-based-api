package com.daviddev16.empresa.impl;

import com.daviddev16.empresa.EmpresaService;
import com.daviddev16.empresa.dto.request.RequestCriarEmpresaDTO;
import com.daviddev16.empresa.entity.Empresa;
import com.daviddev16.empresa.exception.EmpresaNaoEncontradaException;
import com.daviddev16.empresa.repository.EmpresaRepository;
import com.daviddev16.usuario.CargoUsuarioEmpresa;
import com.daviddev16.usuario.UsuarioService;
import com.daviddev16.usuario.entity.Usuario;
import com.daviddev16.usuario.entity.UsuarioEmpresa;
import com.daviddev16.usuario.impl.UsuarioEmpresaService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final UsuarioEmpresaService usuarioEmpresaService;
    private final UsuarioService usuarioService;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository,
                              UsuarioEmpresaService usuarioEmpresaService,
                              UsuarioService usuarioService)
    {
        this.empresaRepository = empresaRepository;
        this.usuarioEmpresaService = usuarioEmpresaService;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Empresa> obterTodasEmpresas() {
        return empresaRepository.findAll();
    }

    @Override
    public Empresa obterEmpresaPorId(Authentication authentication, Long empresaId) {

        /* RECUPERA O USUÁRIO AUTENTICADO POR LOGIN */
        String loginAutenticado = (String) authentication.getPrincipal();
        Usuario usuarioAutentication = usuarioService.obterUsuarioPorLogin(loginAutenticado);

        System.out.println(usuarioAutentication.getId() + ", " + empresaId);
        if (!usuarioEmpresaService.verificarOwnership(usuarioAutentication.getId(), empresaId))
            throw new RuntimeException( String.format("Você não possui acesso a empresa '%d' ou a empresa não existe.", empresaId));

        return empresaRepository
                .findById(empresaId)
                .orElseThrow(() -> new EmpresaNaoEncontradaException(empresaId));
    }

    @Override
    @Transactional
    public Empresa criarEmpresa(Authentication authentication, RequestCriarEmpresaDTO criarEmpresaDTO) {

        /* RECUPERA O USUÁRIO AUTENTICADO POR LOGIN */
        String loginAutenticado = (String) authentication.getPrincipal();
        Usuario usuarioAutentication = usuarioService.obterUsuarioPorLogin(loginAutenticado);

        /* CRIA A EMPRESA */
        Empresa novaEmpresa = new Empresa();
        novaEmpresa.setNome(criarEmpresaDTO.getNomeEmpresa());
        novaEmpresa = empresaRepository.save(novaEmpresa);

        /* CRIA VINCULO COM USUÁRIO AUTENTICADO E A EMPRESA CRIADA */
        UsuarioEmpresa donoUsuarioEmpresa = usuarioEmpresaService
                .criarUsuarioEmpresa(usuarioAutentication, novaEmpresa, CargoUsuarioEmpresa.PROPRIETARIO);

        novaEmpresa.setUsuariosEmpresa(List.of(donoUsuarioEmpresa));

        return novaEmpresa;
    }
}
