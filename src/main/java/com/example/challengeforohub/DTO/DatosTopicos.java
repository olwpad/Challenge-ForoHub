package com.example.challengeforohub.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record DatosTopicos(

        @NotBlank(message = "El titulo no puede ser nulo")
        String titulo,

        @NotBlank(message = "El mensaje no puede ser nulo")
        String mensaje
        ) {


}
