package lk.tech.moonbffuser.controller;

import lk.tech.moonbffuser.dto.auth.*;
import lk.tech.moonbffuser.dto.db.DbUser;
import lk.tech.moonbffuser.dto.db.DbUserCreateRequest;
import lk.tech.moonbffuser.web.AuthClient;
import lk.tech.moonbffuser.web.DbClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/api/v1")
@AllArgsConstructor
public class AuthController {

    private final AuthClient authClient;
    private final DbClient dbClient;

    @PostMapping("/auth/register")
    public AuthUserResponse register(@RequestBody RegisterRequest registerRequest) {
        DbUser user = dbClient.createUser();
        RegisterWithUserIdRequest authRequest = new RegisterWithUserIdRequest(user.getId(), registerRequest.email(), registerRequest.password());
        return authClient.register(authRequest);
    }

    @PostMapping("/auth/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authClient.login(request);
    }

    @PostMapping("/auth/refresh")
    public TokenResponse refresh(@RequestBody RefreshRequest request) {
        return authClient.refresh(request);
    }

    @PostMapping("/auth/logout")
    public void logout(@RequestBody RefreshRequest request) {
        authClient.logout(request);
    }

    @PostMapping("/auth/logout-all")
    public void logoutAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        authClient.logoutAll(authorization);
    }

    @PostMapping("/auth/confirm-email")
    public Map<String, String> confirmEmail(@RequestParam String token) {
        return authClient.confirmEmail(token);
    }

    @PostMapping("/auth/resend-confirmation")
    public Map<String, String> resendConfirmation(@RequestParam String email) {
        return authClient.resendConfirmation(email);
    }

    @PostMapping("/auth/request-password-reset")
    public Map<String, String> requestPasswordReset(@RequestParam String email) {
        return authClient.requestPasswordReset(email);
    }

    @PostMapping("/auth/reset-password")
    public Map<String, String> resetPassword(@RequestBody ResetPasswordRequest request) {
        return authClient.resetPassword(request);
    }

    @PostMapping("/auth/change-password")
    public Map<String, String> changePassword(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @RequestBody ChangePasswordRequest request) {
        return authClient.changePassword(authorization, request);
    }

    @GetMapping("/user/profile")
    public Map<String, String> getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        return authClient.getProfile(authorization);
    }
}