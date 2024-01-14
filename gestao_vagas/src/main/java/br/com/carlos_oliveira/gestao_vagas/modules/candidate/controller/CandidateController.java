package br.com.carlos_oliveira.gestao_vagas.modules.candidate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.CandidateEntity;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@PostMapping("/")
	public void create(@Valid @RequestBody CandidateEntity candidateEntity) {

		System.out.println("Candidato");
		System.out.println(candidateEntity.getEmail());

	}

}
