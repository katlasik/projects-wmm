package pl.sda.projects.services;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.projects.model.User;

import java.util.Optional;

@Service
public class SecurityService {

    private final AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public boolean userIsLoggedIn() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();


        return authentication != null &&
                authentication.isAuthenticated() &&
                !authenticationTrustResolver.isAnonymous(authentication);
    }
    public Optional<User> getLoggedInUser() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByEmail(email);

    }

}
