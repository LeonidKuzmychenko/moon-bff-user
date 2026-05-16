package lk.tech.moonbffuser.web;

import lk.tech.moonbffuser.dto.db.User;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange("/api/v1/users")
public interface DbClient {

    @GetExchange
    List<User> getAll();
}