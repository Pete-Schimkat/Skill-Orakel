package de.inmediasp.skill_orakel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain secureEndpoints (HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                    .authorizeHttpRequests(request -> {
                        request.requestMatchers("/v3/api-docs").permitAll();
                        request.requestMatchers("/api/skillprofile/{id}/**").permitAll();
                        request.anyRequest().authenticated();
                    })
                    .oauth2Login(AbstractAuthenticationFilterConfigurer::permitAll);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
}
