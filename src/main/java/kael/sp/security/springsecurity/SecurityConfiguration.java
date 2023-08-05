package kael.sp.security.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
  
  @Bean
  SecurityFilterChain chain(HttpSecurity http) throws Exception{
    return http
    .authorizeHttpRequests(
      authorizeConfig ->{
        authorizeConfig.requestMatchers("/public").permitAll();
        authorizeConfig.requestMatchers("/logout").permitAll();
        authorizeConfig.anyRequest().authenticated();
      }
    )    
    .oauth2Login(Customizer.withDefaults())
    .oauth2ResourceServer(oauth2 -> {oauth2.jwt(Customizer.withDefaults());})
    .build();
  }

}
