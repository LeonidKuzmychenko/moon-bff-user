package lk.tech.moonbffuser.web;

import lk.tech.moonbffuser.dto.auth.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@HttpExchange("/api/v1")
public interface AuthClient {

    @PostExchange("/auth/register")
    Map<String, String> register(@RequestBody RegisterRequest request);

    @PostExchange("/auth/login")
    TokenResponse login(@RequestBody LoginRequest request);

    @PostExchange("/auth/refresh")
    TokenResponse refresh(@RequestBody RefreshRequest request);

    @PostExchange("/auth/logout")
    void logout(@RequestBody RefreshRequest request);

    @PostExchange("/auth/logout-all")
    void logoutAll(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    );

    @GetExchange("/auth/me")
    UserResponse me(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    );

    @PostExchange("/auth/confirm-email")
    Map<String, String> confirmEmail(@RequestParam String token);

    @PostExchange("/auth/resend-confirmation")
    Map<String, String> resendConfirmation(@RequestParam String email);

    @PostExchange("/auth/request-password-reset")
    Map<String, String> requestPasswordReset(@RequestParam String email);

    @PostExchange("/auth/reset-password")
    Map<String, String> resetPassword(@RequestBody ResetPasswordRequest request);

    @PostExchange("/auth/change-password")
    Map<String, String> changePassword(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @RequestBody ChangePasswordRequest request
    );

    @GetExchange("/auth/health")
    Map<String, String> health();

    @GetExchange("/user/profile")
    Map<String, String> getProfile(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    );

    @GetExchange("/admin/dashboard")
    Map<String, String> getDashboard(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    );
}