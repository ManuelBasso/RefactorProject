// package com.develhope.spring.configurations.login_registration;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.develhope.spring.configurations.dto.UserDTO;
// import com.develhope.spring.configurations.exception.UserRegistrationException;
// import com.develhope.spring.configurations.security.UserService;

// import jakarta.validation.Valid;

// @RestController
// public class RegistrationController {

//     @Autowired
//     private UserService userService;

//     @GetMapping("/registerget")
//     public String showRegistrationForm(Model model) {
//         model.addAttribute("userDTO", new UserDTO());
//         return "registration";
//     }

//     @PostMapping("/registerpost")
//     public String registerUser(@Valid UserDTO userDTO, BindingResult result, Model model) {
//         if (result.hasErrors()) {
//             return "registration";
//         }

//         try {
//             userService.registerUser(userDTO);
//             return "redirect:/login?success";
//         } catch (UserRegistrationException e) {
//             model.addAttribute("error", e.getMessage());
//             return "registration";
//         }
//     }
// }
