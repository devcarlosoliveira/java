package br.com.carlos_oliveira.gestao_vagas.modules.candidate.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.carlos_oliveira.gestao_vagas.exceptions.JobNotFoundException;
import br.com.carlos_oliveira.gestao_vagas.exceptions.UserNotFoundException;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.ApplyJobEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.JobRepository;

/**
 * ApplyJobService
 */
public class ApplyJobService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private ApplyJobRepository applyJobRepository;

	@Transactional
	public ApplyJobEntity create(UUID candidateId, UUID jobId) {

		// validade
		// Objects.requireNonNull(candidateId, "Candidato não pode ser nulo");
		// Objects.requireNonNull(jobId, "Job não pode ser nulo");

		candidateRepository
				.findById(candidateId)
				.orElseThrow(() -> {
					throw new UserNotFoundException();
				});

		jobRepository
				.findById(jobId)
				.orElseThrow(() -> {
					throw new JobNotFoundException();
				});

		var applyJobEntity = ApplyJobEntity.builder()
				.candidateId(candidateId)
				.jobId(jobId)
				.build();

		return applyJobRepository.save(applyJobEntity);
	}
}