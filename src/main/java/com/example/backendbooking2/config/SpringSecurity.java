package com.example.backendbooking2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurity {

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/v1/authCred/signup","/api/v1/authCred/login")) // Exclude CSRF protection for /signup
                .authorizeHttpRequests(authorize -> authorize
                        //Login Controller Endpoints
                        .requestMatchers(HttpMethod.POST,"/authCred/signup","/authCred/login").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/authCred/update/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/authCred/delete/{id}").authenticated()
                        //Customer Controller Endpoints
                        .requestMatchers(HttpMethod.GET,"/customer","/customer/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST,"/customer").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/customer/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/customer/{id}").authenticated()
                        //Order Controller Endpoints
                        .requestMatchers(HttpMethod.GET,"/order").authenticated()
                        .requestMatchers(HttpMethod.POST,"/order").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/order/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/order/{id}").authenticated()
                        //Orderline Controller Endpoints
                        .requestMatchers(HttpMethod.GET,"/orderlines").authenticated()
                        .requestMatchers(HttpMethod.GET,"/orderlines/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/orderlines").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/orderlines/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/orderlines/{id}").authenticated()
                        //Product Controller Endpoints
                        .requestMatchers(HttpMethod.GET,"/product").permitAll()
                        .requestMatchers(HttpMethod.GET,"/product/Category").permitAll()
                        .requestMatchers(HttpMethod.GET,"/product/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/product").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/product/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/product/{id}").authenticated()
                        //User Controller Endpoints
                        .requestMatchers(HttpMethod.GET,"/user").authenticated()
                        .requestMatchers(HttpMethod.GET,"/user/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST,"/user").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/user/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/user/{id}").authenticated()
                        //All Forgotten, Endpoint will be protected.
                        .anyRequest().authenticated()
                );
        return http.build();
    }
    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }

}
