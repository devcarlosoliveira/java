package br.com.carlos_oliveira.gestao_vagas.modules.candidate.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.CandidateEntity;

/**
 * CandidateRepository
 */
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

	Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

	Optional<CandidateEntity> findByUsername(String username);

}