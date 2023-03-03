package com.springboot.springsecurityexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    String[] unauthenticatedApis = new String[] {
            "/home/**",
    };

    String[] adminApis = new String[] {
            "/admin/**"
    };

    String[] authenticatedAPis = new String[] {
            "/user/**"
    };

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.withUsername("Sanjeet")
                .password(passwordEncoder().encode("sk123"))
                .roles("USER")
                .build();

        UserDetails adminUser = User.withUsername("Raj")
                .password(passwordEncoder().encode("raj123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests().requestMatchers(adminApis).hasRole("ADMIN")
                .requestMatchers(unauthenticatedApis).permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers(authenticatedAPis).authenticated()
                .and()
                .formLogin();

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
