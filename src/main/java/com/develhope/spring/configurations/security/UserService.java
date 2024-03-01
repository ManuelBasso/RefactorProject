// package com.develhope.spring.configurations.security;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// import com.develhope.spring.configurations.dto.UserDTO;
// import com.develhope.spring.configurations.exception.UserRegistrationException;

// public interface UserService extends UserDetailsService {
//     UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

//     void registerUser(UserDTO userDTO) throws UserRegistrationException;
// }