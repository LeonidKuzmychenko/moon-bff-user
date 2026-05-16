package lk.tech.moonbffuser.dto.auth;

public record ResetPasswordRequest(
        String token,
        String newPassword
) {
}