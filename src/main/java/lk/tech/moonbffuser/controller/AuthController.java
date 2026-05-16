package lk.tech.moonbffuser.controller;

import lk.tech.moonbffuser.dto.auth.*;
import lk.tech.moonbffuser.web.AuthClient;
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
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthClient authClient;

    public AuthController(AuthClient authClient) {
        this.authClient = authClient;
    }

    @PostMapping("/auth/register")
    public Map<String, String> register(@RequestBody RegisterRequest request) {
        return authClient.register(request);
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