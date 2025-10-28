package com.jei.service;

import com.jei.client.UsuarioClient;
import com.jei.client.UsuarioResponseDto;
import com.jei.dto.LoginRequest;
import com.jei.dto.LoginResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    private final UsuarioClient usuarioClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public AuthService(UsuarioClient usuarioClient, PasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.usuarioClient = usuarioClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    public LoginResponse login(LoginRequest request) {
        System.out.println("Correo recibido service: " + request.getCorreo());
        String correo = request.getCorreo();
        if (correo == null || correo.isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }
        UsuarioResponseDto usuario = usuarioClient.buscarPorCorreo(request.getCorreo());

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!request.getContrasena().equals(usuario.getContrasena())) {
            throw new RuntimeException("Credenciales inválidas");
        }


        Instant now = Instant.now();
        long expiracion = 3600; // 1 hora

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(usuario.getCorreo())
                .claim("nombre", usuario.getNombre())
                .claim("apellido", usuario.getApellido())
                .claim("role", usuario.getRole() != null ? usuario.getRole().toString() : "USER")
                .claim("departamento", usuario.getDepartamento() != null ? usuario.getDepartamento().toString() : "MARKETING")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiracion))
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return LoginResponse.builder()
                .token(token)
                .tipoToken("Bearer")
                .expiraEn(expiracion)
                .usuario(usuario)
                .build();
    }
}