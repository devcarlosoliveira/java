package br.com.carlosoliveira.desafio01.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosoliveira.desafio01.dto.CursoDTO;
import br.com.carlosoliveira.desafio01.model.CursoEntity;
import br.com.carlosoliveira.desafio01.service.CursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // #region GetMapping
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        try {
            List<CursoEntity> cursos = cursoService.findAll();
            return ResponseEntity.ok().body(cursos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        try {
            CursoEntity curso = cursoService.findById(id);
            return ResponseEntity.ok().body(curso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // #endregion

    // #region PostMapping
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CursoEntity curso) {
        try {
            var savedEntity = cursoService.create(curso);
            return ResponseEntity.ok().body(savedEntity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // #endregion

    // #region PutMapping
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody CursoDTO cursoDTO) {
        try {
            CursoEntity curso = cursoService.findById(id);

            if (!(cursoDTO.getName() == null || cursoDTO.getName().trim().isEmpty())) {
                curso.setName(cursoDTO.getName());
            }

            if (!(cursoDTO.getCategory() == null || cursoDTO.getCategory().trim().isEmpty())) {
                curso.setName(cursoDTO.getCategory());
            }

            CursoEntity cursoSaved = cursoService.update(curso);

            return ResponseEntity.ok().body(cursoSaved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    // #endregion

    // #region PatchMapping
    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> active(@PathVariable UUID id) {
        try {
            CursoEntity curso = cursoService.findById(id);
            curso.setActive(!curso.isActive());
            CursoEntity cursoSaved = cursoService.update(curso);

            return ResponseEntity.ok().body(cursoSaved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    // #endregion

    // #region DeleteMapping
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            cursoService.deleteById(id);
            return ResponseEntity.ok().body(new CursoEntity());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // #endregion
}
