package lk.tech.moonbffuser.dto.auth;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        Role role,
        boolean enabled,
        boolean emailVerified,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
    public enum Role {
        USER,
        ADMIN
    }
}