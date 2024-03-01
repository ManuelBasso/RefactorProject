// package com.develhope.spring.configurations.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// import org.springframework.security.web.DefaultSecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

//         @Autowired
//         private SecurityBeansConfig securityBeansConfig;

//         @Bean
//         public AuthenticationManager getAuthenticationManager() throws Exception {
//                 return securityBeansConfig.authenticationManager();
//         }

//         @SuppressWarnings("deprecation")
//         @Override
//         public void configure(HttpSecurity http) throws Exception {
//                 http
//                                 .authorizeRequests(authorize -> authorize
//                                                 .requestMatchers("/public/**").permitAll()
//                                                 .requestMatchers("/user/**").hasRole("ROLE_CUSTOMER")
//                                                 .requestMatchers("/seller/**").hasRole("ROLE_SELLER")
//                                                 .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
//                                                 .anyRequest().authenticated())
//                                 .formLogin(formLogin -> formLogin
//                                                 .loginPage("/login")
//                                                 .permitAll())
//                                 .logout(logout -> logout
//                                                 .logoutSuccessUrl("/login").permitAll());
//         }
// }