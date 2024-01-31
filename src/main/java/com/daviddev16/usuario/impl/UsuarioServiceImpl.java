package com.daviddev16.usuario.impl;

import com.daviddev16.usuario.UsuarioService;
import com.daviddev16.usuario.entity.Usuario;
import com.daviddev16.usuario.exception.UsuarioNaoEncontradoException;
import com.daviddev16.usuario.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(PasswordEncoder passwordEncoder,
                              UsuarioRepository usuarioRepository)
    {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obterUsuarioPorId(Integer usuarioId) {
        return usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() ->
                        new UsuarioNaoEncontradoException(usuarioId));
    }

    @Override
    public Usuario obterUsuarioPorLogin(String login) {
        return usuarioRepository
                .findByLogin(login)
                .orElseThrow(() ->
                        new UsuarioNaoEncontradoException(login));
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setSenha( passwordEncoder.encode(usuario.getSenha()) );
        usuario.setStatusAtivo( true );
        return usuarioRepository.save(usuario);
    }

    @Override
    public void excluirUsuarioPorId(Integer usuarioId) {
        Usuario usuario = obterUsuarioPorId(usuarioId);
        usuarioRepository.delete(usuario);
    }

}
