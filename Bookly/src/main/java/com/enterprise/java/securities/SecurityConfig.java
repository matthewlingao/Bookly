package com.enterprise.java.securities;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// bean for encoding passwords using Bcrypt 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/book/**", "/bookdata/**", "/reviews/**").permitAll()
                .requestMatchers("/saveReview").authenticated()
                // only users with the ADMIN role can access /admin
                .requestMatchers("/admin").hasRole("ADMIN") 
                // only authenticated (ADMINS) users can add books
                .requestMatchers("/saveBook").hasRole("ADMIN")
                //only admin can delete books
                .requestMatchers("/deleteBook/**").hasRole("ADMIN")
                .requestMatchers("/editBook/**", "/updateBook").hasRole("ADMIN")
                // any requests require authentication
                .anyRequest().authenticated())
            .httpBasic(withDefaults())
            .formLogin(withDefaults());

        http.csrf((csrf) -> csrf.disable());
        // if user who is not admin trying to access /admin will be redirected to a denied page
        http.exceptionHandling(x -> x.accessDeniedPage("/denied"));

        return http.build();
    }
}
