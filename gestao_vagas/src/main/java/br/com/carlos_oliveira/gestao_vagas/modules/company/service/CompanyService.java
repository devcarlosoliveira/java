package br.com.carlos_oliveira.gestao_vagas.modules.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlos_oliveira.gestao_vagas.exceptions.UserFoundException;
import br.com.carlos_oliveira.gestao_vagas.modules.company.model.CompanyEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public CompanyEntity create(CompanyEntity companyEntity) {

		companyRepository
				.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
				.ifPresent((user) -> {
					throw new UserFoundException();
				});

		return companyRepository.save(companyEntity);

	}
}
