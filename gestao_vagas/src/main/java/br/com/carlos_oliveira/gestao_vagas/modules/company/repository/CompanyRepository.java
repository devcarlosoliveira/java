package br.com.carlos_oliveira.gestao_vagas.modules.company.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlos_oliveira.gestao_vagas.modules.company.model.CompanyEntity;

/**
 * CompanyRepository
 */
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

	Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);

	Optional<CompanyEntity> findByUsername(String username);

}