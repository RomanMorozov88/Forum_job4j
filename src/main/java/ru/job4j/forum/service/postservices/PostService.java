package ru.job4j.forum.service.postservices;

import ru.job4j.forum.model.Post;

import java.util.List;

public interface PostService {

    void savePost(Post post);

    Post getPostById(Integer id);

    List<Post> getAll();

}
