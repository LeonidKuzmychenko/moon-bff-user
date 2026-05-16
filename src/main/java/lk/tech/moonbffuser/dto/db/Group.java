package lk.tech.moonbffuser.dto.db;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private Long id;
    private String url;
    private String tile;
    private List<Area> areas;
}