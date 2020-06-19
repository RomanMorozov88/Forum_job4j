package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.postservices.PostService;
import ru.job4j.forum.service.utils.GetMainUserUtil;

import java.util.Calendar;

@Controller
public class PostEditControl {

    @Autowired
    private PostService postService;
    @Autowired
    private GetMainUserUtil getMainUserUtil;


    /**
     * Получает на входе id нужного для редактирования поста
     * (или -1 если создаётся новая)
     *
     * @param postId
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String postEditPage(
            @RequestParam(value = "postid", defaultValue = "-1") String postId,
            Model model
    ) {
        int id = Integer.parseInt(postId);
        Post post = new Post();
        post.setId(id);
        if (id != -1) {
            post = this.postService.getPostById(id);
        }
        model.addAttribute("post", post);
        return "edit";
    }


    /**
     * Получает на входе данные для создания\редактирования поста.
     * Проверяет- соответствует ли атор поста и текущий пользователь
     * для успешного сохранения изменений.
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEditNewPost(
            @ModelAttribute Post post
    ) {
        Integer bufferId = post.getId();
        User bufferMainUser = getMainUserUtil.getCurrentUser();
        if (bufferId == -1) {
            post.setCreated(Calendar.getInstance());
            post.setAuthor(bufferMainUser);
        } else {
            Post bufferPost = postService.getPostById(bufferId);
            post.setAuthor(bufferPost.getAuthor());
            post.setCreated(bufferPost.getCreated());
        }
        if (bufferMainUser.equals(post.getAuthor())) {
            postService.savePost(post);
        }
        return "redirect:/";
    }
}
