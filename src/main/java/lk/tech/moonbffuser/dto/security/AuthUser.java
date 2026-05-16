package lk.tech.moonbffuser.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthUser {

    private final UUID authId;
    private final String email;
    private final String role;
}