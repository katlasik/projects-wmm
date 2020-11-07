package pl.sda.projects.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.projects.model.Role;
import pl.sda.projects.model.UserRegistration;
import pl.sda.projects.services.SecurityService;
import pl.sda.projects.services.UserService;
@Controller
public class RegistrationController {

    private final UserService userService;
    private final SecurityService securityService;

    public RegistrationController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("registration")
    public String postRegistration(@ModelAttribute("registration") UserRegistration userRegistration) {
        System.out.println(userRegistration);

        userService.createUser(userRegistration.getUsername(), userRegistration.getEmailAdress(), userRegistration.getPassword(), Role.USER);
        return "registration";
    }
}
