package com.company.pizzaria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;





//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable()) // Mantém CSRF desativado (recomendado para APIs/desenvolvimento)
//            .authorizeHttpRequests(authz -> authz
//                .anyRequest().permitAll() // ⬅️ PERMITE TODAS AS REQUISIÇÕES SEM AUTENTICAÇÃO
//            )
//            // ⬇️ Remove o formLogin e logout (opcional, já que todas as rotas estão liberadas)
//            .formLogin(form -> form.disable())
//            .logout(logout -> logout.disable());
//
//        return http.build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Pode manter, mesmo sem uso temporário
//    }
//}

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable()) // ADICIONE ESTA LINHA
	        .authorizeHttpRequests(authz -> authz
	            .requestMatchers("/admin/**").hasAuthority("ADMIN")
	            .anyRequest().permitAll()
	        )
	        .formLogin(form -> form
	            .loginPage("/admin/login")
	            .defaultSuccessUrl("/admin/dashboard", true)
	            .permitAll()
	        )
	        .logout(logout -> logout.permitAll());
	    return http.build();
	}

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




