package br.com.carlos_oliveira.gestao_vagas.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

/**
 * TestUtils
 */
@UtilityClass
public final class TestUtils {

    public static String objectToJSON(Object object) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID id, String secretKey) {
        var algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("javavagas")
                .withExpiresAt(expiresIn)
                .withSubject(id.toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        return token;
    }
}