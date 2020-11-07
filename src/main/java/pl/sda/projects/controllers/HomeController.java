package pl.sda.projects.controllers;

//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.projects.model.Role;
import pl.sda.projects.model.UserRegistration;
import pl.sda.projects.services.SecurityService;
import pl.sda.projects.services.UserService;
import javax.validation.Valid;

//@Slf4j
@Controller
public class HomeController {

    private final SecurityService securityService;
    private final UserService userService;

    public HomeController(SecurityService securityService, UserService userService) {
        this.securityService = securityService;
        this.userService = userService;
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

}
