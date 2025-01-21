package com.example.challengeforohub.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosUsuarios(
        @NotBlank(message = "El nombre de usuario no puede estar vacío.")
        String login,

        @NotBlank(message = "La clave no puede estar vacía.")
        String clave) {
}
