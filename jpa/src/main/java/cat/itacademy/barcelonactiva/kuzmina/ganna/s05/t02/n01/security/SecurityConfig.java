package cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
//@EnableWebSecurity
public class SecurityConfig{
    private final JwtAuthenticationFilter jwtAuthFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/players/player/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                /*.and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/
                .and()
                //.authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        //.logout()
        // .logoutUrl("/api/v1/auth/logout")
        // .addLogoutHandler(logoutHandler)
        // .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }
}

