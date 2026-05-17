package lk.tech.moonbffuser.dto.auth;

public record RegisterWithUserIdRequest(
        Long dbId,
        String email,
        String password
) {
}