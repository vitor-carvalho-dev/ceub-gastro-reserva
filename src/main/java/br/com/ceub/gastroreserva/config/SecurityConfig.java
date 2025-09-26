package br.com.ceub.gastroreserva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain configspringSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/publica/**").permitAll();
                            authorizeConfig.requestMatchers("/h2-console/**").permitAll();
                            authorizeConfig.requestMatchers("/logout").permitAll();
                            authorizeConfig.requestMatchers("/favicon.ico/**").permitAll();
                            authorizeConfig.anyRequest().authenticated();
                            try {
                                //liberar o h2
                                authorizeConfig.and().headers().frameOptions().disable();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        })
                .csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .cors().disable()
                .build();
    }

}
