package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.postservices.PostServiceList;
import ru.job4j.forum.service.utils.GetMainUserUtil;

@Controller
public class PostControl {

    @Autowired
    private PostServiceList postServiceList;
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
        Post post = this.postServiceList.getPostById(id);
        if (id != -1 && post != null) {
            if (post.getAuthor().equals(this.getMainUserUtil.getCurrentUser())) {
                editFlag = true;
            }
            model.addAttribute("editFlag", editFlag);
            model.addAttribute("currentPost", post);
        } else {
            model.addAttribute("errorMessage", "Post not found.");
        }
        return "post";
    }
}
