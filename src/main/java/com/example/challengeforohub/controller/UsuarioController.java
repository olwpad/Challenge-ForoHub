package com.example.challengeforohub.controller;


import com.example.challengeforohub.DTO.DatosUsuarios;
import com.example.challengeforohub.domain.Usuario;
import com.example.challengeforohub.infra.errores.ValidacionException;
import com.example.challengeforohub.infra.security.DatosJWTToken;
import com.example.challengeforohub.infra.security.TokenService;
import com.example.challengeforohub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody @Valid DatosUsuarios datos) throws ValidacionException {
        usuarioService.createUsuario(datos);
        return ResponseEntity.created(null).build();
    }

    // Autenticación de usuario (inicio de sesión)
    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody @Valid DatosUsuarios datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
