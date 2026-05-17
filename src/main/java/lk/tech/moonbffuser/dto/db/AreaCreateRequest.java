package lk.tech.moonbffuser.dto.db;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request to create a new area")
public class AreaCreateRequest {

    @NotNull(message = "Area ID is mandatory")
    @Schema(description = "External Area ID", example = "500")
    private Long id;
}
