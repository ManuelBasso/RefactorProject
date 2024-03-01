// package com.develhope.spring.configurations.security;

// import java.util.Set;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.develhope.spring.user.Users;
// import com.develhope.spring.configurations.dto.UserDTO;
// import com.develhope.spring.configurations.exception.UserRegistrationException;
// import com.develhope.spring.user.UserRepository;

// @Service
// public class UserDetailsServiceImpl implements UserService {

//     @Autowired
//     UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         Users user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

//         Set<GrantedAuthority> authorities = user.getRole().stream()
//                 .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNome().name()))
//                 .collect(Collectors.toSet());

//         return new User(user.getEmail(), user.getPassword(), authorities);
//     }

//     @Override
//     public void registerUser(UserDTO userDTO) throws UserRegistrationException {
//         if (userRepository.existsByEmail(userDTO.getEmail())) {
//             throw new UserRegistrationException("Email already in use");
//         }

//         Users user = new Users();
//         user.setEmail(userDTO.getEmail());
//         user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//         user.setLastName(userDTO.getLastName());
//         user.setFirstName(userDTO.getFirstName());

//         userRepository.save(user);
//     }
// }
