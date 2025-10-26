package com.jei.client;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private Role role;
    private String contrasena;
    private Departamento departamento;
}
