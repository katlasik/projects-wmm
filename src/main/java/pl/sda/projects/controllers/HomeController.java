package pl.sda.projects.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.projects.model.User;
import pl.sda.projects.services.SecurityService;
import pl.sda.projects.services.UserService;

@Controller
public class HomeController {
    private final UserService userService;
    private final SecurityService securityService;

    public HomeController(SecurityService securityService, UserService userService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/")
    public String getWelcome() {
        return "welcome";
    }

    @GetMapping("/login")
    public String getLogin(Model model, @RequestParam(defaultValue = "false") boolean error) {
        if (securityService.userIsLoggedIn()) {
            return "redirect:";
        } else {
			model.addAttribute("error", error);
			return "login";
		}
    }
    @GetMapping("/profile")
    public String getProfile(Model model) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        String user = userService.getUserByEmail(email).map(u -> u.getName()).orElse("");

        model.addAttribute("name", user);
        model.addAttribute("email", email);

        return "profile";
    }




}
