package br.com.carlos_oliveira.gestao_vagas.modules.company.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlos_oliveira.gestao_vagas.modules.company.model.JobEntity;

/**
 * JobRepository
 */
public interface JobRepository extends JpaRepository<JobEntity, UUID> {

	List<JobEntity> findAllByDescriptionContainingIgnoreCase(String filter);

}