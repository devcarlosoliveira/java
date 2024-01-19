package br.com.carlos_oliveira.gestao_vagas.modules.candidate.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlos_oliveira.gestao_vagas.modules.auth.service.ProfileCandidateService;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.CandidateEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.service.CandidateService;
import br.com.carlos_oliveira.gestao_vagas.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private ProfileCandidateService profileCandidateService;

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {

		try {

			var savedEntity = candidateService.create(candidateEntity);

			return ResponseEntity.ok().body(savedEntity);
			// return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
	}

	@GetMapping("/")
	@PreAuthorize("hasRole('CANDIDATE')")
	public ResponseEntity<Object> get(HttpServletRequest request) {

		var candidateId = request.getAttribute(Constants.CANDIDATE_ID);

		try {
			var profile = profileCandidateService.getProfileCandidate(UUID.fromString(candidateId.toString()));
			return ResponseEntity.ok().body(profile);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
