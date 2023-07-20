package com.cibertec.proyectoDAWeb_ii;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cibertec.proyectoDAWeb_ii.service.CustomUserDetailService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	CustomUserDetailService userDetailsService() {
		return new CustomUserDetailService();
	}

	
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){                
        return new BCryptPasswordEncoder();
    }
    	
    
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(
				csrf ->
				csrf.disable())			
			.authorizeHttpRequests(
				auth -> 
				auth
					.requestMatchers("/").permitAll()
					.requestMatchers("/inventarios/*").hasRole("USER")
					.requestMatchers("/mantenimientos/*").hasRole("ADMIN")
					.anyRequest().authenticated()
			);
		http
			.formLogin(
				form -> 
				form.loginPage("/login").permitAll()
					.failureUrl("/login-error")
					.defaultSuccessUrl("/", true));
		http
			.logout(
					out ->
					out.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
				);
		http
			.exceptionHandling(
				exception ->
				exception.accessDeniedPage("/error403"));
		
		return http.build();
	}
	
}
