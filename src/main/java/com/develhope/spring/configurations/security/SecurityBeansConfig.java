// package com.develhope.spring.configurations.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// public class SecurityBeansConfig {

//     @Autowired
//     UserService userService;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager() throws Exception {
//         AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(null);
//         builder.userDetailsService(userService)
//                 .passwordEncoder(passwordEncoder());
//         return builder.build();
//     }

//     @Bean
//     public SecurityConfiguration securityConfiguration() throws Exception {
//         return new SecurityConfiguration(authenticationManager(), passwordEncoder());
//     }
// }