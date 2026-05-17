package lk.tech.moonbffuser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lk.tech.moonbffuser.dto.db.AreaCreateRequest;
import lk.tech.moonbffuser.dto.db.DbArea;
import lk.tech.moonbffuser.web.DbClient;
import lombok.AllArgsConstructor;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/area")
@AllArgsConstructor
public class DbAreaController {

    private final DbClient dbClient;

    @PostMapping("/group/{groupId}")
    @Operation(summary = "Create a new area for a group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Area created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data or Area already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Group not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public DbArea create(@PathVariable Long groupId, @Valid @RequestBody AreaCreateRequest request) {
        return dbClient.createArea(groupId, request);
    }

    @GetMapping("/group/{groupId}")
    @Operation(summary = "Get all areas for a specific group")
    public List<DbArea> getByGroup(@PathVariable Long groupId) {
        return dbClient.getAreasByGroupId(groupId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete area by ID")
    public void delete(@PathVariable Long id) {
        dbClient.deleteArea(id);
    }
}
