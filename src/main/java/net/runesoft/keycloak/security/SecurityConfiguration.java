package net.runesoft.keycloak.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.oauth2Client().and().oauth2Login();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http.authorizeHttpRequests((auth) -> auth.requestMatchers("/", "/login/**", "/oauth2/**").permitAll().requestMatchers("/runesoft/*").permitAll().anyRequest().fullyAuthenticated());

        http.logout(logout -> logout.logoutSuccessUrl("http://localhost:8080/realms/runesoft/protocol/openid-connect/logout?redirect_uri=http://localhost:8080/"));

        return http.build();
    }
}