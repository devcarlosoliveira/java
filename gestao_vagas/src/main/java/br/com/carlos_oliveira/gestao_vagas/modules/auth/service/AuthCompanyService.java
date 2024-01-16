package br.com.carlos_oliveira.gestao_vagas.modules.auth.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.carlos_oliveira.gestao_vagas.exceptions.UsernameNotFoundException;
import br.com.carlos_oliveira.gestao_vagas.modules.auth.dto.AuthCompanyDTO;
import br.com.carlos_oliveira.gestao_vagas.modules.company.model.CompanyEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class AuthCompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * @param authCompanyDTO
	 * @throws AuthenticationException
	 */
	public void authentication(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

		var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
				() -> {
					throw new UsernameNotFoundException();
				});

		var passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

		if (!passwordMatches) {
			throw new AuthenticationException();
		}
	}

}
