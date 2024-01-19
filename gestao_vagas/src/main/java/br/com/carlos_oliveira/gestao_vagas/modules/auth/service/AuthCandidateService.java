package br.com.carlos_oliveira.gestao_vagas.modules.auth.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.carlos_oliveira.gestao_vagas.exceptions.UsernameNotFoundException;
import br.com.carlos_oliveira.gestao_vagas.modules.auth.dto.AuthCandidateRequestDTO;
import br.com.carlos_oliveira.gestao_vagas.modules.auth.dto.AuthCandidateResponseDTO;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.CandidateEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class AuthCandidateService {

	@Value("${security.token.secret.candidate}")
	private String secretKey;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * @param authCompanyDTO
	 * @return
	 * @throws AuthenticationException
	 */
	public AuthCandidateResponseDTO authentication(AuthCandidateRequestDTO authCandidateDTO)
			throws AuthenticationException {

		CandidateEntity candidateEntity = candidateRepository.findByUsername(authCandidateDTO.username())
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("Username or Password incorrect");
				});

		boolean passwordMatches = passwordEncoder.matches(authCandidateDTO.password(), candidateEntity.getPassword());

		if (!passwordMatches) {
			throw new AuthenticationException("Username or Password incorrect");
		}

		var algorithm = Algorithm.HMAC256(secretKey);
		var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

		var token = JWT.create()
				.withIssuer("javavagas")
				.withExpiresAt(expiresIn)
				.withSubject(candidateEntity.getId().toString())
				.withClaim("roles", Arrays.asList("CANDIDATE"))
				.sign(algorithm);

		var authCandidateResponse = AuthCandidateResponseDTO.builder()
				.access_token(token)
				.expires_in(expiresIn.toEpochMilli())
				.build();

		return authCandidateResponse;

	}
}
