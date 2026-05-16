package lk.tech.moonbffuser.controller;

import lk.tech.moonbffuser.dto.security.AuthUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/api/v1")
public class MeController {

    @GetMapping("/register")
    public Map<String, Object> me(@AuthenticationPrincipal AuthUser user) {
//        X-Auth-User-Id:
//        X-Auth-User-Email:
//        X-Auth-User-Role:
        return Map.of(
                "authId", user.getAuthId(),
                "email", user.getEmail(),
                "role", user.getRole()
        );
    }
}