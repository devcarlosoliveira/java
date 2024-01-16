package br.com.carlos_oliveira.gestao_vagas.modules.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * AuthCompanyDTO
 */
@Data
@AllArgsConstructor
public class AuthCompanyDTO {

	private String username;
	private String password;

}