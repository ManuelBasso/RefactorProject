// package com.develhope.spring.configurations.login_registration;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import com.develhope.spring.configurations.dto.UserDTO;
// import com.develhope.spring.configurations.security.SecurityConfiguration;

// import jakarta.validation.Valid;

// @RestController
// public class LoginController {

//     @Autowired
//     private SecurityConfiguration securityConfiguration; 

//     @GetMapping("/login")
//     public String showLoginForm(Model model) {
//         model.addAttribute("userDTO", new UserDTO());
//         return "login";
//     }

//     @PostMapping("/login")
//     public String processLogin(@Valid UserDTO userDTO, BindingResult bindingResult, Model model,
//             RedirectAttributes redirectAttributes) {
//         if (bindingResult.hasErrors()) {
//             return "login";
//         }

//         try {

//             UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
//                     userDTO.getPassword());
//             Authentication authentication = securityConfiguration.authenticate(token);
//             SecurityContextHolder.getContext().setAuthentication(authentication);

//             return "redirect:/home";
//         } catch (AuthenticationException e) {

//             model.addAttribute("error", "Login fallito. Controlla le tue credenziali.");
//             return "login";
//         }
//     }
// }