package br.com.carlos_oliveira.gestao_vagas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Gestão de Vagas")
						.description("API Responsável pela gestão de vagas")
						.version("1"))
				.schemaRequirement("jwt_auth", customSecurityScheme());
	}

	// exemple for all routes
	/*
	 * return new OpenAPI()
	 * .info(new Info()
	 * .title("Gestão de Vagas")
	 * .description("API Responsável pela gestão de vagas")
	 * .version("1"))
	 * .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
	 * .components(new Components().addSecuritySchemes("Bearer Authentication",
	 * customSecurityScheme()));
	 */

	private SecurityScheme customSecurityScheme() {
		return new SecurityScheme()
				.name("jwt_auth")
				.type(SecurityScheme.Type.HTTP)
				.scheme("bearer")
				.bearerFormat("JWT");
	}

}
