package lk.tech.moonbffuser.dto.auth;

import java.time.OffsetDateTime;
import java.util.UUID;

public record LoginRequest(
        String email,
        String password
) {
}