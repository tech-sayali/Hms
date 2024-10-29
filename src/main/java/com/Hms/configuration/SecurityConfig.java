package com.Hms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    )throws Exception
    {
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        //haap
       // http.authorizeHttpRequests().anyRequest().permitAll();
        http.authorizeHttpRequests().
                requestMatchers("/api/v1/user/*")
                .permitAll()
                .requestMatchers("/api/v1/country").hasRole("OWNER")
                .anyRequest().authenticated();

        return http.build();
    }
}
