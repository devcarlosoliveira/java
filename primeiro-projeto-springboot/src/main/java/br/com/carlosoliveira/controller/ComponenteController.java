package br.com.carlosoliveira.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosoliveira.service.ComponenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/componente")
public class ComponenteController {

	@Autowired
	ComponenteService componenteService;

	@GetMapping("/")
	public String chamandoComponente() {

		var resultado = componenteService.chamarComponente();
		return resultado;
	}

}
