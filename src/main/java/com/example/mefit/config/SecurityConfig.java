package com.example.mefit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Enable CORS -- this is further configured on the controllers
                .cors().and()
                // Sessions will not be used
                .sessionManagement().disable()
                // Disable CSRF -- not necessary when there are no sessions
                .csrf().disable()
                // Enable security for http requests
                .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(HttpMethod.GET, "/api/v1/exercises").hasAuthority("Contributor")
                    //.requestMatchers(HttpMethod.GET, "/api/v1/workouts").hasAuthority("Contributor")
                    //.requestMatchers(HttpMethod.GET, "/api/v1/goals").hasRole("Administrator")
                    //.requestMatchers(HttpMethod.GET, "/api/v1/profiles").hasAuthority("Administrator")
                    // All remaining paths require authentication
                    .anyRequest().authenticated()
                )
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtRoleAuthenticationConverter());
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtRoleAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // Use roles claim as authorities
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        // Add the ROLE_ prefix - for hasRole
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
