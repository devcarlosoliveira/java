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
import br.com.carlos_oliveira.gestao_vagas.modules.auth.dto.AuthCompanyDTO;
import br.com.carlos_oliveira.gestao_vagas.modules.auth.dto.AuthCompanyResponseDTO;
import br.com.carlos_oliveira.gestao_vagas.modules.company.model.CompanyEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class AuthCompanyService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @param authCompanyDTO
     * @return
     * @throws AuthenticationException
     */
    public AuthCompanyResponseDTO authentication(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

        CompanyEntity companyEntity = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Username or Password incorrect");
                });

        boolean passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), companyEntity.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException("Username or Password incorrect");
        }

        var algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("javavagas")
                .withExpiresAt(expiresIn)
                .withSubject(companyEntity.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompanyResponse = AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCompanyResponse;

    }
}
