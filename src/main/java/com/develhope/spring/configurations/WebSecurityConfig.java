/*
package com.develhope.spring.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/admin").hasRole("ROLE_ADMIN")
						.requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
						.requestMatchers("/seller").hasRole("ROLE_SELLER")
						.requestMatchers("/seller/**").hasRole("ROLE_SELLER")
						.requestMatchers("/").permitAll()
						.anyRequest().authenticated())
				.formLogin((form) -> form
						.loginPage("/login")
						.permitAll())
				.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		// da modificare 
		PasswordEncoder encoder = BCryptPasswordEncoder.getInstance();

		UserDetails user1 = User.builder()
				.username("admin")
				.password(encoder.encode(null))
				.roles("ROLE_ADMIN", "ROLE_USER")
				.build();

		UserDetails user2 = User.builder()
				.username("seller")
				.password(encoder.encode(null))
				.roles("ROLE_SELLER")
				.build();

		return new InMemoryUserDetailsManager(user1, user2);
	}
}*/

