package br.com.carlos_oliveira.gestao_vagas.modules.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.carlos_oliveira.gestao_vagas.exceptions.UserFoundException;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.CandidateEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public CandidateEntity create(CandidateEntity candidateEntity) {
		candidateRepository
				.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
				.ifPresent((user) -> {
					throw new UserFoundException();
				});

		var password = passwordEncoder.encode(candidateEntity.getPassword());
		candidateEntity.setPassword(password);

		return candidateRepository.save(candidateEntity);

	}
}
