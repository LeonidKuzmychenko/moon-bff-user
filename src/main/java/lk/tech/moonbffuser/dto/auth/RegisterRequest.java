package lk.tech.moonbffuser.dto.auth;

public record RegisterRequest(
        String email,
        String password
) {
}