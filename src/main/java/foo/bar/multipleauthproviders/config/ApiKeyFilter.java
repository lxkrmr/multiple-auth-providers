package foo.bar.multipleauthproviders.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiKeyFilter extends OncePerRequestFilter {

    private final CustomAuthManager customAuthManager = new CustomAuthManager();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String apiKey = request.getHeader("x-api-key");
        if(apiKey == null) {
            filterChain.doFilter(request, response);
            return;
        }

        var apiKeyAuthentication = ApiKeyAuthentication.unauthenticated(apiKey);
        var result = customAuthManager.authenticate(apiKeyAuthentication);
        if (result.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(result);
            filterChain.doFilter(request, response);
        }
    }
}
