package com.example.pinkbullmakeup.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints (accessible to all)
                        .requestMatchers(
                                "/api/auth/**",                    // login/register
                                "/api/products/**",                // viewing products
                                "/api/categories/**",              // viewing categories
                                "/api/brands/**",                  // viewing brands
                                "/api/messages",                   // send message via contact us
                                "/api/chatbot/**"                  // chatbot interactions
                        ).permitAll()

                        // Customer-only actions (e.g., cart, orders)
                        .requestMatchers("/api/cart/**", "/api/orders/**", "/api/customer/**").hasRole("CUSTOMER")

                        // Admin-only actions (e.g., adding, updating, deleting products, categories, and brands)
                        .requestMatchers(
                                "/api/products/add/**",          // Admin can add products
                                "/api/products/update/**",       // Admin can update products
                                "/api/products/delete/**",       // Admin can delete products
                                "/api/categories/add/**",        // Admin can add categories
                                "/api/categories/update/**",     // Admin can update categories
                                "/api/categories/delete/**",     // Admin can delete categories
                                "/api/brands/add/**",            // Admin can add brands
                                "/api/brands/update/**",         // Admin can update brands
                                "/api/brands/delete/**"          // Admin can delete brands
                        ).hasRole("ADMIN")

                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
