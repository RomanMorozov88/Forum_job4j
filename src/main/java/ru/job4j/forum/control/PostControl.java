package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.postservices.PostService;
import ru.job4j.forum.service.utils.GetMainUserUtil;

@Controller
public class PostControl {

    @Autowired
    private PostService postService;
    @Autowired
    private GetMainUserUtil getMainUserUtil;

    /**
     * Получает на входе id нужного поста, которую затем достаёт из postService.
     * Если на входе было -1- то возвращает сообщение ошибки.
     *
     * @param postId
     * @param model
     * @return
     */
    @GetMapping("/post")
    public String postEditPage(
            @RequestParam(value = "id", defaultValue = "-1") String postId,
            Model model
    ) {
        boolean editFlag = false;
        int id = Integer.parseInt(postId);
        if (id != -1) {
            Post post = this.postService.getPostById(id);
            if (post != null) {
                User bufferUser = this.getMainUserUtil.getCurrentUser();
                if (post.getAuthor().equals(bufferUser)) {
                    editFlag = true;
                }
                model.addAttribute("editFlag", editFlag);
                model.addAttribute("currentPost", post);
            }
        } else {
            model.addAttribute("errorMessage", "Post not found.");
        }
        return "post";
    }
}
