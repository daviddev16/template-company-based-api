package com.daviddev16.config;

import com.daviddev16.usuario.entity.Usuario;
import com.daviddev16.usuario.UsuarioService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioService usuarioService;

    public UsuarioAuthenticationProvider(UsuarioService usuarioService,
                                         PasswordEncoder passwordEncoder)
    {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String senha = authentication.getCredentials().toString();

        Usuario usuario = usuarioService.obterUsuarioPorLogin(login);

        if (passwordEncoder.matches(senha, usuario.getSenha()))
        {
            GrantedAuthority basicAuthority = new SimpleGrantedAuthority("ROLE_USUARIO");
            return new UsernamePasswordAuthenticationToken(usuario.getLogin(), null, List.of(basicAuthority));
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
