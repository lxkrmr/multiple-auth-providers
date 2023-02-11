package foo.bar.multipleauthproviders.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ApiKeyAuthentication implements Authentication {

    private final boolean authenticated;
    private final String key;

    public ApiKeyAuthentication(boolean authenticated, String key) {
        this.authenticated = authenticated;
        this.key = key;
    }

    public static ApiKeyAuthentication unauthenticated(String key) {
        return new ApiKeyAuthentication(false, key);
    }

    public static ApiKeyAuthentication authenticated() {
        return new ApiKeyAuthentication(true, null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Don't use setter, create a new ApiKeyAuthentication instead");
    }

    @Override
    public String getName() {
        return null;
    }

    public String getKey() {
        return key;
    }
}
