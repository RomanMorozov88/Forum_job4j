package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.userservices.UserService;

@Controller
public class RegistrationControl {

    @Autowired
    private UserService userService;

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
            @ModelAttribute User newUser
    ) {
        String way = "redirect:/login";
        if (!userService.saveUser(newUser)) {
            way = "redirect:/reg?error=true";
        }
        return way;
    }
}
