package com.example.challengeforohub.service;
import com.example.challengeforohub.DTO.DatosTopicos;
import com.example.challengeforohub.domain.Topico;
import com.example.challengeforohub.infra.errores.ValidacionException;
import com.example.challengeforohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {


    @Autowired
    private TopicoRepository topicoRepository;


    public void createTopico(DatosTopicos datos) {
        var topico = new Topico(datos.titulo(), datos.mensaje(),datos.idUsuario());
        topicoRepository.save(topico);

    }

    public List<Topico> getAllTopicos() throws ValidacionException {
    var topicos = topicoRepository.findAll();
    if (topicos.isEmpty()){
        throw new ValidacionException("No hay tópicos");
    }
    return topicos;
    }

    public Topico getTopicoById(Long id) throws ValidacionException {

        if(!topicoRepository.existsById(id)){
            throw new ValidacionException("El tópico no existe");
        }
        return topicoRepository.findById(id).orElse(null);
    }

    public void updateTopico(Long id, DatosTopicos datos) throws ValidacionException {

        var topico = topicoRepository.findById(id).orElse(null);
        if (topico == null) {
            throw new ValidacionException("El tópico no existe");

        }
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topicoRepository.save(topico);

    }

    public void deleteTopico(Long id) throws ValidacionException {
        if (!topicoRepository.existsById(id)) {
            throw new ValidacionException("El tópico no existe");
        }
        topicoRepository.deleteById(id);


    }
}
