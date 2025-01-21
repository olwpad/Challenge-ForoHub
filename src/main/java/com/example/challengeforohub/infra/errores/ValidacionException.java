package com.example.challengeforohub.infra.errores;

public class ValidacionException extends Throwable {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
