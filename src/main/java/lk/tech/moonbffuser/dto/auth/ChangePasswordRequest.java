package lk.tech.moonbffuser.dto.auth;

public record ChangePasswordRequest(
        String currentPassword,
        String newPassword
) {
}