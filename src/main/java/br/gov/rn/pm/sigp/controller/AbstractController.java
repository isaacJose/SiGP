package br.gov.rn.pm.sigp.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;

import br.gov.rn.pm.sigp.model.Role;
import br.gov.rn.pm.sigp.model.User;
import br.gov.rn.pm.sigp.service.UserService;

public class AbstractController {
    
    private User overridenCurrentUser;

    @Autowired
    private UserService userService;

    protected void setUserAuth(ModelAndView modelAndView) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", user.getPatente() + " " + user.getName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("authorities", getRoles(user));
    }
    
    protected String getUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication == null)
            return null;

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    protected User getCurrentUser() {
        if (overridenCurrentUser != null) {
            return overridenCurrentUser;
        }

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();

        User user = userService.findUserByEmail(auth.getName());

        return user;
    }

    protected String getRoles(User user) {
        Set<Role> roles = user.getRoles();
        String str = "[";
        int size = roles.size();
        int i = 1;
        for (Role role : roles) {
            str += role.getRole();
            if (i > 0 && i < size) {
                str += ", ";
                i += 1;
            }
        }
        return str + "]";
    }

}
