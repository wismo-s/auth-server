package com.jei.security;

import com.jei.client.UsuarioClient;
import com.jei.client.UsuarioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioClient usuarioClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioResponseDto usuario = usuarioClient.buscarPorCorreo(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        return User.withUsername(usuario.getCorreo())
                .password(usuario.getContrasena())
                .roles(String.valueOf(usuario.getRole()))
                .build();
    }
}
