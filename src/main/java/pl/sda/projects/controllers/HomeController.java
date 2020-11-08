package pl.sda.projects.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.projects.model.forms.ChangePasswordForm;
import pl.sda.projects.model.Role;
import pl.sda.projects.model.UserRegistration;
import pl.sda.projects.services.SecurityService;
import pl.sda.projects.services.UserService;

import javax.validation.Valid;
import pl.sda.projects.services.UserService;
import javax.validation.Valid;

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
        var user = securityService.getLoggedInUser().orElseThrow();
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @GetMapping("/changePassword")
    public String getPassword(Model model, @RequestParam(defaultValue = "false") boolean error){
        model.addAttribute("changePassword",new ChangePasswordForm("","",""));
            return "changePassword";
    }
    @PostMapping("/changePassword")
    public String updatePassword(Model model, @ModelAttribute("changePassword") @Valid ChangePasswordForm form,
                                 BindingResult bindingResult, RedirectAttributes attributes) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            bindingResult.rejectValue("newPassword", "change.password.errorMsg.passwordMismatch");
        } else if (!bindingResult.hasErrors()) {
            boolean passwordChanged = userService.changePassword(email, form.getCurrentPassword(), form.getNewPassword());
            if (passwordChanged) {
                attributes.addFlashAttribute("success", "change.password.success");
                return "redirect:/profile";
            } else {
                bindingResult.rejectValue("currentPassword", "change.password.errorMsg.wrongOldPassword");
                return "changePassword";

            }

        }
        return "changePassword";
    }


}
