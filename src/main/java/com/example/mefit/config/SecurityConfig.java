package com.example.mefit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.http.SessionCreationPolicy ;
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
            //.cors().and()
            // Sessions will not be used
            // Disable CSRF -- not necessary when there are no sessions
            //.csrf().disable()
            // Enable security for http requests
            .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/v1/exercises").hasAuthority("Contributor")
                //.requestMatchers(HttpMethod.GET, "/api/v1/workouts").hasAuthority("Contributor")
                //.requestMatchers(HttpMethod.GET, "/api/v1/goals").hasRole("Administrator")
                //.requestMatchers(HttpMethod.GET, "/api/v1/profiles").hasAuthority("Administrator")
                // All remaining paths require authentication
                .anyRequest().authenticated();
                
        //http.oauth2ResourceServer()
        //   .jwt()
        //   .jwtAuthenticationConverter(jwtRoleAuthenticationConverter());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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
