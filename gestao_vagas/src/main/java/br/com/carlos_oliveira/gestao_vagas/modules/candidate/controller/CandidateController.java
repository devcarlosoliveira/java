package br.com.carlos_oliveira.gestao_vagas.modules.candidate.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlos_oliveira.gestao_vagas.modules.auth.dto.ProfileCandidateResponseDTO;
import br.com.carlos_oliveira.gestao_vagas.modules.auth.service.ProfileCandidateService;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.CandidateEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.service.CandidateService;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.service.JobService;
import br.com.carlos_oliveira.gestao_vagas.modules.company.model.JobEntity;
import br.com.carlos_oliveira.gestao_vagas.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Candidato", description = "Informações do candidato")
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private ProfileCandidateService profileCandidateService;

	@Autowired
	private JobService jobService;

	@Operation(summary = "Cadastro do candidato",

			description = "Essa função é responsável por cadastrar um candidato"

	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = CandidateEntity.class))

			}),
			@ApiResponse(responseCode = "400", description = "Usuário já existe")
	})
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
	@Operation(summary = "Perfil do candidato",

			description = "Essa função é responsável por buscar as informações do perfil do candidato"

	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))

			}),
			@ApiResponse(responseCode = "400", description = "User not found")
	})
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<Object> get(HttpServletRequest request) {

		var candidateId = request.getAttribute(Constants.CANDIDATE_ID);

		try {
			var profile = profileCandidateService.getProfileCandidate(UUID.fromString(candidateId.toString()));
			return ResponseEntity.ok().body(profile);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/job")
	@PreAuthorize("hasRole('CANDIDATE')")
	@Operation(summary = "Lista de vagas disponível para o candidato",

			description = "Essa função é responsável por listar todas as vagas disponíveis, baseada no filtro"

	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))

			})
	})
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<Object> findAllJob(@RequestParam String filter) {
		return ResponseEntity.ok().body(jobService.listAllJobs(filter));
	}
}
