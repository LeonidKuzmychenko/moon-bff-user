package lk.tech.moonbffuser.dto.auth;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}