package com.jobportal.backend.config;

import com.jobportal.backend.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .formLogin(form -> form.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // PUBLIC APIs
                        .requestMatchers("/error", "/api/auth/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/jobs/**").permitAll()

                        // RECRUITER ONLY
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/jobs/**").hasRole("RECRUITER")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/jobs/**").hasRole("RECRUITER")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/jobs/**").hasRole("RECRUITER")

                        // USER ONLY (apply job)
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/applications/**").hasRole("USER")

                        // baaki sab secured
                        .anyRequest().authenticated()
                );


        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
