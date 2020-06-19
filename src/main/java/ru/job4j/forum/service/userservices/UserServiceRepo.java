package ru.job4j.forum.service.userservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * UserService с использованием UserRepository extends CrudRepository<User, Long>
 */
@Service
public class UserServiceRepo implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public UserServiceRepo() {
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public boolean saveUser(User user) {
        boolean result = false;
        if (this.getByName(user.getName()) == null) {
            String bufferPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(bufferPassword);
            this.userRepository.save(user);
            result = true;
        }
        return result;
    }

    @Override
    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }
}
