package lk.tech.moonbffuser.config.security;

import lk.tech.moonbffuser.dto.security.AuthUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtBffAuthenticationToken extends AbstractAuthenticationToken {

    private final AuthUser principal;

    public JwtBffAuthenticationToken(
            AuthUser principal,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(authorities);
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public AuthUser getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return "";
    }
}