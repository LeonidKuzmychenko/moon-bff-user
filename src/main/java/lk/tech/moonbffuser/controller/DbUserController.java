package lk.tech.moonbffuser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lk.tech.moonbffuser.dto.db.DbUser;
import lk.tech.moonbffuser.dto.security.AuthUser;
import lk.tech.moonbffuser.web.DbClient;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class DbUserController {

    private final DbClient dbClient;

    @GetMapping("/current")
    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "500", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public DbUser get(@AuthenticationPrincipal AuthUser user) {
        return dbClient.getUser(user.getDbId());
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public List<DbUser> getAll() {
        return dbClient.getAllUsers();
    }
}
