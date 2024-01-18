package br.com.carlos_oliveira.gestao_vagas.modules.auth.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.carlos_oliveira.gestao_vagas.exceptions.UsernameNotFoundException;
import br.com.carlos_oliveira.gestao_vagas.modules.auth.dto.ProfileCandidateResponseDTO;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.CandidateEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.repository.CandidateRepository;

public class ProfileCandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	public ProfileCandidateResponseDTO getProfileCandidate(UUID candidateId) {

		if (candidateId == null) {
			throw new NullPointerException("FieldError must not be null");
		}

		CandidateEntity candidateEntity = candidateRepository.findById(candidateId)
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("Username or Password incorrect");
				});

		var profileCandidateResponseDTO = ProfileCandidateResponseDTO.builder()
				.id(candidateEntity.getId())
				.name(candidateEntity.getName())
				.description(candidateEntity.getDescription())
				.username(candidateEntity.getUsername())
				.email(candidateEntity.getEmail())
				.build();

		return profileCandidateResponseDTO;
	}
}
