package br.com.carlos_oliveira.gestao_vagas.modules.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.carlos_oliveira.gestao_vagas.exceptions.UserFoundException;
import br.com.carlos_oliveira.gestao_vagas.modules.company.model.CompanyEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public CompanyEntity create(CompanyEntity companyEntity) {

		companyRepository
				.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
				.ifPresent((user) -> {
					throw new UserFoundException();
				});

		var password = passwordEncoder.encode(companyEntity.getPassword());
		companyEntity.setPassword(password);

		return companyRepository.save(companyEntity);

	}
}
