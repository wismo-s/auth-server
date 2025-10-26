package com.jei.dto;

import com.jei.client.UsuarioResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private String tipoToken;
    private long expiraEn;
    private UsuarioResponseDto usuario;
}
