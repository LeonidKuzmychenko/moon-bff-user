package lk.tech.moonbffuser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lk.tech.moonbffuser.dto.db.DbGroup;
import lk.tech.moonbffuser.dto.minio.TileDto;
import lk.tech.moonbffuser.dto.security.AuthUser;
import lk.tech.moonbffuser.web.DbClient;
import lk.tech.moonbffuser.web.MinioClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/v1/group")
@AllArgsConstructor
public class DbGroupController {

    private final DbClient dbClient;
    private final MinioClient minioClient;

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE
    );

    @PostMapping
    public DbGroup create(@AuthenticationPrincipal AuthUser user, @RequestParam("url") String url, @RequestPart("tile") MultipartFile tile) {
        log.info("Add Group REQUEST userId={}, url={}, tile={}", user.getDbId(), url, tile.getOriginalFilename());
        if (tile.getSize() > 1024 * 1024) {// 1 MB
            throw new IllegalArgumentException("Tile file size must not exceed 1 MB");
        }
        if (!ALLOWED_CONTENT_TYPES.contains(tile.getContentType())) {
            throw new IllegalArgumentException("Only PNG, JPEG images are allowed");
        }
        TileDto tileDto = minioClient.uploadTile(tile);
        log.info("Add Group REQUEST userId={}, url={}, tileId={}", user.getDbId(), url, tileDto.getTileId());
        return dbClient.createGroup(user.getDbId(), url, tileDto.getTileId());
    }

    @GetMapping
    @Operation(summary = "Get all groups for a specific user")
    public List<DbGroup> getByUser(@AuthenticationPrincipal AuthUser user) {
        return dbClient.getGroupsByUserId(user.getDbId());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get group by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group found"),
            @ApiResponse(responseCode = "500", description = "Group not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public DbGroup get(@PathVariable Long id) {
        return dbClient.getGroup(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete group by ID")
    public void delete(@PathVariable Long id) {
        DbGroup deletedGroup = dbClient.getGroup(id);
        minioClient.deleteTile(deletedGroup.getTile());
        dbClient.deleteGroup(deletedGroup.getId());
    }
}
