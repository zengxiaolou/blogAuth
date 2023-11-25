package com.ruler.auth.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        RequestMatcher staticResources = PathRequest.toStaticResources().atCommonLocations();
        RequestMatcher swaggerUi = new AntPathRequestMatcher("/swagger-ui/**");
        RequestMatcher apiDocs = new AntPathRequestMatcher("/v3/api-docs/**");
        RequestMatcher swaggerHtml = new AntPathRequestMatcher("/swagger-ui.html");

        RequestMatcher publicEndpoints = new OrRequestMatcher(staticResources, swaggerUi, apiDocs, swaggerHtml);

        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(publicEndpoints).permitAll()
                .requestMatchers("/auth/**").authenticated()
                .anyRequest().permitAll()
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
