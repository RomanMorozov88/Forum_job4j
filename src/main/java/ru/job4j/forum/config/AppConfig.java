package ru.job4j.forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.job4j.forum.service.postservices.PostService;
import ru.job4j.forum.service.postservices.PostServiceList;
import ru.job4j.forum.service.postservices.PostServiceRepo;
import ru.job4j.forum.service.userservices.UserService;
import ru.job4j.forum.service.userservices.UserServiceList;
import ru.job4j.forum.service.userservices.UserServiceRepo;

/**
 * Определяем нужные нам реализации PostService и UserService
 */
@Configuration
@ComponentScan("ru.job4j.forum.service")
public class AppConfig {

    @Bean
    public PostService postService() {
        return new PostServiceRepo();
    }

    @Bean
    public UserService userService() {
        return new UserServiceRepo();
    }

}