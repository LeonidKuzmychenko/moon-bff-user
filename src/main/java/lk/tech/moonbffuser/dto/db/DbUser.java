package lk.tech.moonbffuser.dto.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbUser {
    private Long id;
    private List<DbGroup> groups = new ArrayList<>();
}