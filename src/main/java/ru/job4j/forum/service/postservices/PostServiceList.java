package ru.job4j.forum.service.postservices;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Сервис для хранения постов в List.
 */
@Service
public class PostServiceList implements PostService {

    private final List<Post> posts = new ArrayList<>();
    private static int idCount = 1;

    public PostServiceList() {
    }

    @Override
    public void savePost(Post post) {
        if (post.getId() == null) {
            post.setId(idCount++);
            this.posts.add(post);
        } else {
            this.updatePost(post);
        }
    }

    /**
     * Т.к. в метод savePost может поступить пост для редактирования-
     * вызывается этот метод, заменяющий имеющийся пост новым.
     *
     * @param post
     */
    private void updatePost(Post post) {
        Integer checkingId = post.getId();
        ListIterator<Post> iterator = this.posts.listIterator();
        while (iterator.hasNext()) {
            Post next = iterator.next();
            if (next.getId().equals(checkingId)) {
                iterator.set(post);
            }
        }
    }

    @Override
    public Post getPostById(Integer id) {
        Post result = null;
        for (Post p : this.posts) {
            if (p.getId() == id) {
                result = p;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Post> getAll() {
        return posts;
    }
}