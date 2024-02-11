package br.com.carlosoliveira.desafio01.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.carlosoliveira.desafio01.model.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, UUID> {

}
