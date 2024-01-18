package br.com.carlos_oliveira.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private SecurityFilter securityFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf(CSRF -> CSRF.disable())
				.authorizeHttpRequests(auth -> {
					auth
							.requestMatchers("/candidate/auth/").permitAll()
							.requestMatchers("/company/auth/").permitAll()
							.requestMatchers("/candidate/").permitAll()
							.requestMatchers("/company/").permitAll();
					auth
							.anyRequest().authenticated();
				})
				.addFilterBefore(securityFilter, BasicAuthenticationFilter.class);

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
}
