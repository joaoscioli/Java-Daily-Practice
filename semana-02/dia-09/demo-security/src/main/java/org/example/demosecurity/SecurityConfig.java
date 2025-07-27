package org.example.demosecurity;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())   // Desativar CSRF para APIs REST
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/login").permitAll()  // Liberar endpoint de login
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Apenas ROLE_ADMIN
                        .requestMatchers("/user/**").hasRole("USER")    // Apenas ROLE_USER
                        .anyRequest().authenticated()   // Outros endpoints requerem authenticação
                );
        // Aqui adicionaremos o filtro JWT mais tarde
        return http.build();
    }
}
