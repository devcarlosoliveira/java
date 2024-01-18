package br.com.carlos_oliveira.gestao_vagas.modules.auth.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlos_oliveira.gestao_vagas.modules.auth.dto.AuthCandidateRequestDTO;
import br.com.carlos_oliveira.gestao_vagas.modules.auth.service.AuthCandidateService;
import br.com.carlos_oliveira.gestao_vagas.modules.auth.service.ProfileCandidateService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

	@Autowired
	private AuthCandidateService authCandidateDTOService;

	@Autowired
	private ProfileCandidateService profileCandidateService;

	@PostMapping("/auth")
	public ResponseEntity<Object> authentication(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
		try {
			var authentication = authCandidateDTOService.authentication(authCandidateRequestDTO);
			return ResponseEntity.ok().body(authentication);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}

	@GetMapping("/")
	public ResponseEntity<Object> getMethodName(HttpServletRequest request) {
		try {
			var candidateId = request.getAttribute("candidate_id");
			var profileCandidate = profileCandidateService.getProfileCandidate(UUID.fromString(candidateId.toString()));
			return ResponseEntity.ok().body(profileCandidate);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
