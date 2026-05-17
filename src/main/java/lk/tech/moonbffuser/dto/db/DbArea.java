package lk.tech.moonbffuser.dto.db;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbArea {
    @NotNull(message = "Area ID is mandatory")
    @Schema(description = "External Area ID", example = "500")
    private Long id;
}