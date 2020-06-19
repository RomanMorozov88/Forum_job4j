package ru.job4j.forum.service.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Post findById(Integer id);
}