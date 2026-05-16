package lk.tech.moonbffuser.service;

import lk.tech.moonbffuser.utils.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SomeService {

    private final CurrentUserProvider currentUserProvider;

//    public void doSomething() {
//        AuthUser user = currentUserProvider.getCurrentUser();
//
//        var authId = user.authId();
//        var email = user.email();
//        var role = user.role();
//    }
}