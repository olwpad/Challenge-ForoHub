package com.example.challengeforohub.controller;


import com.example.challengeforohub.DTO.DatosTopicos;
import com.example.challengeforohub.domain.Topico;
import com.example.challengeforohub.infra.errores.ValidacionException;
import com.example.challengeforohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Crear un nuevo tópico
    @PostMapping
    public ResponseEntity<?> createTopico(@RequestBody @Valid DatosTopicos datos) {
        topicoService.createTopico(datos);
        return ResponseEntity.created(null).build();
    }

    // Listar todos los tópicos
    @GetMapping
    public ResponseEntity<List<Topico>> getAllTopicos() throws ValidacionException {
        List<Topico> topicos = topicoService.getAllTopicos();
        return ResponseEntity.ok(topicos);
    }

    // Obtener un tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Topico> getTopicoById(@PathVariable Long id) throws ValidacionException {
        Topico topico = topicoService.getTopicoById(id);
        return  ResponseEntity.ok(topico);
    }

    // Actualizar un tópico
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopico(@PathVariable Long id, @RequestBody @Valid DatosTopicos datos) throws ValidacionException {
        topicoService.updateTopico(id, datos);
        return ResponseEntity.ok().build();
    }

    // Eliminar un tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopico(@PathVariable Long id) throws ValidacionException {
        topicoService.deleteTopico(id);
        return ResponseEntity.noContent().build();
    }
}
