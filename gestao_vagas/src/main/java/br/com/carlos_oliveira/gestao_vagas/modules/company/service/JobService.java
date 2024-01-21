package br.com.carlos_oliveira.gestao_vagas.modules.company.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.carlos_oliveira.gestao_vagas.modules.company.model.JobEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	@Transactional
	public JobEntity create(JobEntity jobEntity) {

		Objects.requireNonNull(jobEntity, "A entidade não pode ser nula");

		return jobRepository.save(jobEntity);

	}
}
