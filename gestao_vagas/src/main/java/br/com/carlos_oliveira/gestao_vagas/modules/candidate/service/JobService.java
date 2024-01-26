package br.com.carlos_oliveira.gestao_vagas.modules.candidate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlos_oliveira.gestao_vagas.modules.company.model.JobEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	public List<JobEntity> listAllJobs(String filter) {

		return jobRepository.findAllByDescriptionContainingIgnoreCase(filter);

	}
}
