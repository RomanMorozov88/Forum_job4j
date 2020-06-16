package ru.job4j.forum.service.userservices;

import ru.job4j.forum.model.User;

import java.util.List;

public interface UserService {
    User getByName(String name);

    List<User> getAllUsers();

    boolean saveUser(User user);

    void deleteUser(User user);
}
