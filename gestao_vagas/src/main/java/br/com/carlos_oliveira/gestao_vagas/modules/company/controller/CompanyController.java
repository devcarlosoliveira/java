package br.com.carlos_oliveira.gestao_vagas.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlos_oliveira.gestao_vagas.modules.company.model.CompanyEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.service.CompanyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {

		try {

			var savedEntity = companyService.create(companyEntity);

			return ResponseEntity.ok().body(savedEntity);
			// return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}

	}

}
