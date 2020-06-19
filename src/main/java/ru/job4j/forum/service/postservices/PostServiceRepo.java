package ru.job4j.forum.service.postservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * PostService с использованием PostRepository extends CrudRepository<User, Long>
 */
@Service
public class PostServiceRepo implements PostService {

    @Autowired
    private PostRepository postRepository;

    public PostServiceRepo() {
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getPostById(Integer id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getAll() {
        List<Post> result = new ArrayList<>();
        postRepository.findAll().forEach(result::add);
        return result;
    }
}
