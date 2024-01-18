package br.com.carlos_oliveira.gestao_vagas.modules.auth.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {
	private UUID id;
	private String name;
	private String description;
	private String username;
	private String email;

}
