package br.com.carlos_oliveira.gestao_vagas.modules.company.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "job")
public class JobEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotBlank(message = "O campo [description] é obrigatório.")
	private String description;

	private String benefits;

	private String level;

	private UUID companyId;

	@ManyToOne
	@JoinColumn(name = "company_id", insertable = false, updatable = false)
	private CompanyEntity company;

	@CreationTimestamp
	private LocalDateTime createAt;

	@UpdateTimestamp
	private LocalDateTime updateAt;

}
