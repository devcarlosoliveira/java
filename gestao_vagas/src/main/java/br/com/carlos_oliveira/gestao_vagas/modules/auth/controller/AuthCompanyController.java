package br.com.carlos_oliveira.gestao_vagas.modules.auth.controller;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlos_oliveira.gestao_vagas.modules.auth.dto.AuthCompanyDTO;
import br.com.carlos_oliveira.gestao_vagas.modules.auth.service.AuthCompanyService;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

	@Autowired
	private AuthCompanyService authCompanyService;

	@PostMapping("/company")
	public ResponseEntity<Object> authentication(@RequestBody AuthCompanyDTO authCompanyDTO) {
		try {
			var authentication = authCompanyService.authentication(authCompanyDTO);
			return ResponseEntity.ok().body(authentication);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}

}
