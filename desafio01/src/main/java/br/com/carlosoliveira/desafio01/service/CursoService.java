package br.com.carlosoliveira.desafio01.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosoliveira.desafio01.exception.NotFoundException;
import br.com.carlosoliveira.desafio01.model.CursoEntity;
import br.com.carlosoliveira.desafio01.repository.CursoRepository;
import jakarta.transaction.Transactional;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<CursoEntity> findAll() {
        return cursoRepository.findAll();
    }

    public CursoEntity findById(UUID id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado com o ID: " + id));
    }

    @Transactional
    public CursoEntity create(CursoEntity curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public CursoEntity update(CursoEntity curso) {
        if (!cursoRepository.existsById(curso.getId())) {
            throw new NotFoundException("Curso não encontrado com o ID: " + curso.getId());
        }
        return cursoRepository.save(curso);
    }

    @Transactional
    public void deleteById(UUID id) {
        cursoRepository.deleteById(id);
    }

}
