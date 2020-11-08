package pl.sda.projects.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.projects.model.Role;
import pl.sda.projects.model.UserRegistrationForm;
import pl.sda.projects.services.SecurityService;
import pl.sda.projects.services.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final SecurityService securityService;

    public RegistrationController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String getRegistration(@ModelAttribute("registration") UserRegistrationForm user) {
        if (securityService.userIsLoggedIn()) {
            return "redirect";
        } else {
            return "registration";
        }
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute("registration") @Valid UserRegistrationForm user,
                                   BindingResult bindingResult, RedirectAttributes attributes) {

        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            bindingResult.rejectValue("password", "registration.errorMsg.passwordMismatch");
        } else if (userService.checkIfNameIsTaken(user.getUsername())) {
            bindingResult.rejectValue("username", "registration.errorMsg.nameTaken");
        } else if (userService.checkIfMailIsTaken(user.getEmailAdress())) {
            bindingResult.rejectValue("emailAdress", "registration.errorMsg.emailTaken");
        } else if (!bindingResult.hasErrors()) {
            userService.createUser(user.getUsername(), user.getEmailAdress(), user.getPassword(), Role.USER);
            attributes.addFlashAttribute("success", "registration.success");
            return "redirect:/login";
        }

        return "registration";
    }
}
