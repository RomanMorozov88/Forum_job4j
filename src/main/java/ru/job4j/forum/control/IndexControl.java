package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.postservices.PostService;
import ru.job4j.forum.service.utils.GetMainUserUtil;

@Controller
public class IndexControl {

    @Autowired
    private PostService postService;
    @Autowired
    private GetMainUserUtil getMainUserUtil;

    /**
     * Возвращает список существующих постов
     * + текущего пользователя.
     *
     * @param model
     * @return
     */
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", postService.getAll());
        model.addAttribute("currentuser", this.getMainUserUtil.getCurrentUserDetails());
        return "index";
    }
}