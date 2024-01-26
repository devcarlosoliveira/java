package br.com.carlos_oliveira.gestao_vagas.modules.candidate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.ApplyJobEntity;

/**
 * ApplyJobRepository
 */
public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

}