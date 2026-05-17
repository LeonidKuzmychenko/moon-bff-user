package lk.tech.moonbffuser.dto.auth;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserResponse {

    private UUID id;
    private String email;
    private Long dbId;
    private String passwordHash;
    private Role role;
    private boolean enabled = false;
    private boolean emailVerified = false;
    private int failedLoginAttempts = 0;
    private LocalDateTime lockUntil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
