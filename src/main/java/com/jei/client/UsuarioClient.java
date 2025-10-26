package com.jei.client;

import com.jei.config.FeignSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ms-usuario",
        url = "http://localhost:8085/api/usuario",
        configuration = FeignSecurityConfig.class
)
public interface UsuarioClient {

    @GetMapping("/correo/{correo}")
    UsuarioResponseDto buscarPorCorreo(@PathVariable("correo") String correo);
}
