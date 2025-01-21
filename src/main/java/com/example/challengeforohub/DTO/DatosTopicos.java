package com.example.challengeforohub.DTO;

import jakarta.validation.constraints.NotNull;
public record DatosTopicos(
        @NotNull
        Long id,

        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        String descripcion) {


}
