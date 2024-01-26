package br.com.carlos_oliveira.gestao_vagas.modules.candidate.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.carlos_oliveira.gestao_vagas.modules.company.model.JobEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "apply_jobs")
public class ApplyJobEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "candidate_id")
	private UUID candidateId;

	@ManyToOne
	@JoinColumn(name = "candidate_id", insertable = false, updatable = false)
	private CandidateEntity candidateEntity;

	@Column(name = "job_id")
	private UUID jobId;

	@ManyToOne
	@JoinColumn(name = "job_id", insertable = false, updatable = false)
	private JobEntity jobEntity;

	@CreationTimestamp
	private LocalDateTime createAt;

	@UpdateTimestamp
	private LocalDateTime updateAt;
}
