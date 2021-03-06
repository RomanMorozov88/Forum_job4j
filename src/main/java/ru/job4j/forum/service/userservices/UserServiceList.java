package ru.job4j.forum.service.userservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Сервис для хранения пользователей в List.
 */
@Service
public class UserServiceList implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<User> users = new CopyOnWriteArrayList<>();

    public UserServiceList() {
    }

    @Override
    public User getByName(String name) {
        User result = null;
        for (User u : this.users) {
            if (u.getName().equals(name)) {
                result = u;
                break;
            }
        }
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        return this.users;
    }

    @Override
    public boolean saveUser(User user) {
        boolean result = false;
        if (this.getByName(user.getName()) == null) {
            String bufferPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(bufferPassword);
            this.users.add(user);
            result = true;
        }
        return result;
    }

    @Override
    public void deleteUser(User user) {
        this.users.remove(user);
    }
}
