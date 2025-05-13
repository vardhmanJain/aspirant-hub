package dev.vardhman.auth;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dev.vardhman.dto.UserDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthenticationService {

    public String generateJwt(UserDTO user) {
        Set<String> roles = new HashSet<>(Arrays.asList("admin", "user"));

        return Jwt.issuer("aspirant-hub")
                .upn(String.valueOf(user.getId()))
                .subject(String.valueOf(user.getEmail()))
                .groups(roles)
                .expiresAt(System.currentTimeMillis() + 2592000000l)
                .sign();
    }
}
