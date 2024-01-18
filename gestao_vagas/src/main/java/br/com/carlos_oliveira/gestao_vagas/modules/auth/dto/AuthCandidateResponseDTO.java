package br.com.carlos_oliveira.gestao_vagas.modules.auth.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthCandidateResponseDTO {

	private String access_token;
	private Long expires_in;

}
