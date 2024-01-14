package br.com.carlos_oliveira.gestao_vagas.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlos_oliveira.gestao_vagas.modules.company.model.JobEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.service.JobService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	private JobService jobService;

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody JobEntity jobEntity) {

		try {

			var savedEntity = jobService.create(jobEntity);

			return ResponseEntity.ok().body(savedEntity);
			// return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}

	}

}
