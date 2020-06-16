package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.postservices.PostServiceList;
import ru.job4j.forum.service.utils.GetMainUserUtil;

import java.util.Calendar;

@Controller
public class PostEditControl {

    @Autowired
    private PostServiceList postServiceList;
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
            post = this.postServiceList.getPostById(id);
        }
        model.addAttribute("post", post);
        return "edit";
    }


    /**
     * Получает на входе данные для создания\редактирования поста.
     * Проверяет- соответствует ли атор поста и текущий пользователь
     * для успешного сохранения изменений.
     *
     * @param postId
     * @param postname
     * @param postdesc
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEditNewPost(
            @RequestParam(value = "postid", defaultValue = "-1") String postId,
            @RequestParam(value = "postname", required = false) String postname,
            @RequestParam(value = "postdesc", required = false) String postdesc
    ) {
        int id = Integer.parseInt(postId);
        Post post = new Post();
        if (id == -1) {
            post.setCreated(Calendar.getInstance());
            post.setAuthor(getMainUserUtil.getCurrentUser());
        } else {
            post = postServiceList.getPostById(id);
        }
        if (getMainUserUtil.getCurrentUser().equals(post.getAuthor())) {
            post.setName(postname);
            post.setDesc(postdesc);
            postServiceList.savePost(post);
        }
        return "redirect:/";
    }
}
