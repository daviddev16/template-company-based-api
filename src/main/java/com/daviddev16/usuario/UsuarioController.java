package com.daviddev16.usuario;

import com.daviddev16.usuario.dto.ResponseUsuarioDTO;
import com.daviddev16.usuario.entity.Usuario;
import com.daviddev16.usuario.impl.UsuarioServiceImpl;
import com.daviddev16.usuario.transformer.ResponseUsuarioTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ResponseUsuarioTransformer usuarioTransformer;

    public UsuarioController(UsuarioServiceImpl usuarioService,
                             ResponseUsuarioTransformer usuarioTransformer)
    {
        this.usuarioService = usuarioService;
        this.usuarioTransformer = usuarioTransformer;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseUsuarioDTO> obterTodosUsuarios() {
        return usuarioTransformer
                .converterListaUsuarioEmListaUsuarioDto( usuarioService.obterTodosUsuarios() );
    }

    @GetMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseUsuarioDTO obterUsuarioPorId( @PathVariable Integer usuarioId ) {
        return usuarioTransformer
                .converterUsuarioEmUsuarioDto( usuarioService.obterUsuarioPorId(usuarioId) );
    }

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUsuarioDTO registrarUsuario( @RequestBody Usuario usuario ) {
        return usuarioTransformer
                .converterUsuarioEmUsuarioDto( usuarioService.registrarUsuario(usuario) );
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String excluirUsuarioPorId(Integer usuarioId) {
        usuarioService.excluirUsuarioPorId(usuarioId);
        return "Usuário cancelado.";
    }
}
