package lk.tech.moonbffuser.dto.db;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbGroup {
    @Schema(description = "Group ID", example = "10")
    private Long id;

    @NotBlank(message = "URL is mandatory")
    @Schema(description = "Link to the group content", example = "https://example.com/group")
    private String url;

    @NotBlank(message = "Tile is mandatory")
    @Schema(description = "Display title or identifier for the group tile", example = "Group A")
    private String tile;

    @Schema(description = "List of areas within this group")
    private List<DbArea> areas;
}