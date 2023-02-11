package foo.bar.multipleauthproviders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and()
                .addFilterBefore(
                        new ApiKeyFilter(),
                        BasicAuthenticationFilter.class
                )
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .build();
    }
}
