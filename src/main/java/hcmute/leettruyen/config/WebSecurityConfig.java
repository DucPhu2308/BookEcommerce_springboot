package hcmute.leettruyen.config;

import hcmute.leettruyen.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableGlobalAuthentication
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/auth/login",
                                "/api/v1/auth/register",
                                "/api/v1/auth/confirm",
                                "/api/v1/upload/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/book/**").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/genre/**").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "api/v1/comment/**").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/rating/**").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/chapter/**").permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/genre/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/v1/genre/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,
                                "/api/v1/genre/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/book/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/v1/book/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/chapter/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/comment/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/v1/comment/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/payment/**").hasAnyRole("USER")
                        .anyRequest().authenticated());
        return httpSecurity.build();
    }
}
