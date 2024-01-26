package br.com.carlos_oliveira.gestao_vagas.modules.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.carlos_oliveira.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.carlos_oliveira.gestao_vagas.modules.company.model.JobEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.CompanyRepository;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public JobEntity create(JobEntity jobEntity) {

        companyRepository.findById(jobEntity.getId())
                .orElseThrow(() -> {
                    throw new CompanyNotFoundException();
                });

        return jobRepository.save(jobEntity);

    }
}
