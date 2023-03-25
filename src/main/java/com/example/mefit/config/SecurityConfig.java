package com.example.mefit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

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
                .authorizeHttpRequests(authorize -> authorize

                    .requestMatchers(HttpMethod.GET, "/api/v1/exercises").hasAuthority("ROLE_Contributor")
                    //.requestMatchers(HttpMethod.GET, "/api/v1/workouts").hasAuthority("Contributor")
                    //.requestMatchers(HttpMethod.GET, "/api/v1/goals").hasRole("Administrator")
                    //.requestMatchers(HttpMethod.GET, "/api/v1/profiles").hasAuthority("Administrator")
                    // All remaining paths require authentication
                    .anyRequest().authenticated()
                );

        http.oauth2ResourceServer()
           .jwt()
           .jwtAuthenticationConverter(jwtAuthenticationConverterForKeycloak());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
            Collection<String> roles = realmAccess.get("roles");
            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());
        };

        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }
}
