package com.daviddev16.usuario;

import com.daviddev16.usuario.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> obterTodosUsuarios();

    Usuario obterUsuarioPorLogin(String login);

    Usuario obterUsuarioPorId(Integer usuarioId);

    Usuario registrarUsuario(Usuario usuario);

    void excluirUsuarioPorId(Integer usuarioId);

}
