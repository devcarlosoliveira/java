package br.com.carlos_oliveira.gestao_vagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.carlos_oliveira.gestao_vagas.providers.JWTProvider;
import br.com.carlos_oliveira.gestao_vagas.util.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCompanyFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var header = request.getHeader(Constants.HEADER_AUTHORIZATION);

        if (request.getRequestURI().startsWith(Constants.URI_COMPANY)) {
            if (header != null) {
                var tokenDecoded = jwtProvider.validateToken(header);
                if (tokenDecoded == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                request.setAttribute(Constants.COMPANY_ID, tokenDecoded.getSubject());

                var roles = tokenDecoded.getClaim(Constants.ROLES).asList(Object.class);

                var grants = roles.stream()
                        .map(role -> new SimpleGrantedAuthority(Constants.ROLE_ + role.toString().toUpperCase()))
                        .toList();

                var auth = new UsernamePasswordAuthenticationToken(tokenDecoded.getSubject(), null, grants);

                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        }

        filterChain.doFilter(request, response);
    }

}
