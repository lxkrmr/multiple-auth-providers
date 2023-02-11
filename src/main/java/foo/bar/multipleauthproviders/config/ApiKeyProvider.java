package foo.bar.multipleauthproviders.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiKeyProvider implements AuthenticationProvider {

    public static final String API_KEY_WHICH_WE_DO_NOT_DO_IN_PROD_THIS_WAY = "top-secret";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var apiKeyAuthentication = (ApiKeyAuthentication) authentication;
        if (API_KEY_WHICH_WE_DO_NOT_DO_IN_PROD_THIS_WAY.equals(apiKeyAuthentication.getKey())) {
            return ApiKeyAuthentication.authenticated();
        }

        throw new BadCredentialsException("Nope, this are not the correct credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
