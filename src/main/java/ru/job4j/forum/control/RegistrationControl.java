package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.Roles;
import ru.job4j.forum.service.userservices.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationControl {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/reg")
    public String registrationPage(
            @RequestParam(value = "error", required = false) String error,
            Model model
    ) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Пользователь с таким именем уже существует";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registrationUser(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "role", required = false) String role,
            @Valid User newUser,
            BindingResult bindingResult,
            Model model
    ) {
        String way = "redirect:/login";
        newUser.setUserName(username);
        newUser.setUserPassword(passwordEncoder.encode(password));
        newUser.setRole(Roles.valueOf(role));
        if (bindingResult.hasErrors()) {
            way = "redirect:/reg";
        }
        if (!userService.saveUser(newUser)) {
            way = "redirect:/reg?error=true";
        }
        return way;
    }
}
