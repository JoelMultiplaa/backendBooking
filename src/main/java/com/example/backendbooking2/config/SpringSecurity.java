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
                .csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(authorize -> authorize
                        //Login Controller Endpoints
                        .requestMatchers(HttpMethod.POST,"/api/v1/authCred/signup","/api/v1/authCred/login").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/v1/authCred/update/").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/authCred/delete/").authenticated()
                        //Customer Controller Endpoints
                        .requestMatchers("/api/customer/**").authenticated()

                        /*.requestMatchers(HttpMethod.GET,"/api/customer","/api/customer/").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/customer").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/api/customer/").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/customer/").authenticated()*/

                        //Order Controller Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/v1").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/v1").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/v1/").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/").authenticated()
                        //Orderline Controller Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/orderlines").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/orderlines/").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/orderlines").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/orderlines/").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/orderlines/").authenticated()
                        //Product Controller Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/product").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/product/Category").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/product/").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/product").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/api/product/").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/product/").authenticated()
                        //User Controller Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/user").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/user/").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/user").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/api/user/").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/user/").authenticated()
                        //Anything coming in from, browser side is permited.
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

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
