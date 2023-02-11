package foo.bar.multipleauthproviders.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthManager implements AuthenticationManager {

    private final ApiKeyProvider apiKeyProvider = new ApiKeyProvider();

    public CustomAuthManager() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (apiKeyProvider.supports(authentication.getClass())) {
            return apiKeyProvider.authenticate(authentication);
        }

        throw new ProviderNotFoundException("No provider found");
    }
}
