package lk.tech.moonbffuser.web;

import lk.tech.moonbffuser.dto.db.*;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("/api/v1")
public interface DbClient {

    @PostExchange("/user")
    DbUser createUser();

    @GetExchange("/user/{id}")
    DbUser getUser(@PathVariable Long id);

    @GetExchange("/user")
    List<DbUser> getAllUsers();

    @PostExchange(value = "/group")
    DbGroup createGroup(
            @RequestParam("userId") Long userId,
            @RequestParam("url") String url,
            @RequestParam("tileId") String tileId
    );

    @GetExchange("/group/user/{userId}")
    List<DbGroup> getGroupsByUserId(@PathVariable Long userId);

    @GetExchange("/group/{id}")
    DbGroup getGroup(@PathVariable Long id);

    @DeleteExchange("/group/{id}")
    void deleteGroup(@PathVariable Long id);

    @PostExchange("/area/group/{groupId}")
    DbArea createArea(
            @PathVariable Long groupId,
            @RequestBody AreaCreateRequest request
    );

    @GetExchange("/area/group/{groupId}")
    List<DbArea> getAreasByGroupId(@PathVariable Long groupId);

    @DeleteExchange("/area/{id}")
    void deleteArea(@PathVariable Long id);
}