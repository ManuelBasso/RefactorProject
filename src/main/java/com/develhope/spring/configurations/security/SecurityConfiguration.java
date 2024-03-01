// package com.develhope.spring.configurations.security;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.crypto.password.PasswordEncoder;

// public class SecurityConfiguration {

//     private final AuthenticationManager authenticationManager;
//     private final PasswordEncoder passwordEncoder;

//     public SecurityConfiguration(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
//         this.authenticationManager = authenticationManager;
//         this.passwordEncoder = passwordEncoder;
//     }

//     // Configura la sicurezza web
//     @SuppressWarnings("deprecation")
//     public void configure(HttpSecurity http) throws Exception {
//         http
//                 .authorizeRequests(authorize -> authorize
//                         .requestMatchers("/public/**").permitAll()
//                         .requestMatchers("/user/**").hasRole("ROLE_CUSTOMER")
//                         .requestMatchers("/seller/**").hasRole("ROLE_SELLER")
//                         .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
//                         .anyRequest().authenticated())
//                 .formLogin(formLogin -> formLogin
//                         .loginPage("/login")
//                         .permitAll())
//                 .logout(logout -> logout
//                         .logoutSuccessUrl("/login").permitAll());
//     }

//     public Authentication authenticate(UsernamePasswordAuthenticationToken token) throws AuthenticationException {
//         return authenticationManager.authenticate(token); // Delegate the authentication to the injected AuthenticationManager
//     }
// }