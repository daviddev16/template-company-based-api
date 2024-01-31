package com.daviddev16.config;

import com.daviddev16.usuario.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioService usuarioService;
    private final UsuarioAuthenticationProvider usuarioAuthenticationProvider;

    public SecurityConfig(UsuarioService usuarioService,
                          UsuarioAuthenticationProvider usuarioAuthenticationProvider)
    {
        this.usuarioService = usuarioService;
        this.usuarioAuthenticationProvider = usuarioAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(costumizer ->
                {
                    costumizer
                            .requestMatchers(HttpMethod.POST, "/v1/api/usuario/**")
                            .permitAll();

                    costumizer
                            .anyRequest()
                            .authenticated();
                })
                .authenticationProvider(usuarioAuthenticationProvider)
                .httpBasic(Customizer.withDefaults())
                .build();
    }


}
