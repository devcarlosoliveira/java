package br.com.carlos_oliveira.gestao_vagas.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.CandidateEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.repository.CandidateRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private CandidateRepository candidateRepository;

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {

		// Verifica se a entidade não está nula
		if (candidateEntity == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A entidade não pode ser nula");
		}

		// Processa a requisição normalmente se a entidade não estiver nula
		var savedEntity = this.candidateRepository.save(candidateEntity);

		// Retorna uma resposta de sucesso
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);

	}

}
