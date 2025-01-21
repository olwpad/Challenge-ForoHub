package com.example.challengeforohub.service;

import com.example.challengeforohub.DTO.DatosUsuarios;
import com.example.challengeforohub.domain.Usuario;
import com.example.challengeforohub.infra.errores.ValidacionException;
import com.example.challengeforohub.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inyectamos el PasswordEncoder

    public void createUsuario(DatosUsuarios datos) throws ValidacionException {

        if (usuarioRepository.findByLogin(datos.login()) != null) {

            throw new ValidacionException("El usuario ya existe");
        }
        // Encriptamos la contraseña antes de guardarla
        String passwordEncriptada = passwordEncoder.encode(datos.clave());

        // Creamos el usuario con la contraseña encriptada
        var usuario = new Usuario(null, datos.login(), passwordEncriptada);

        // Guardamos el usuario en la base de datos
        usuarioRepository.save(usuario);
    }
}
