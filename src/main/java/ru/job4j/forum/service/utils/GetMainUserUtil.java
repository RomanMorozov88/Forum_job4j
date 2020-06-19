package ru.job4j.forum.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.userservices.UserService;

/**
 * Содержит методы для избавления от повторябщего кода
 * в следующих классах:
 * IndexControl
 * PostEditControl
 * PostControl
 */
@Component
public class GetMainUserUtil {

    @Autowired
    private UserService userService;

    public GetMainUserUtil() {
    }

    /**
     * Метод возвращает авторизованного в данной сессии пользователя
     * если он есть.
     *
     * @return UserDetails
     */
    public UserDetails getCurrentUserDetails() {
        UserDetails result = null;
        Object buffer = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (buffer instanceof UserDetails) {
            result = (UserDetails) buffer;
        }
        return result;
    }

    /**
     * Получаем пользователя
     *
     * @return User
     */
    public User getCurrentUser() {
        User result = null;
        UserDetails bufferUserDetails = this.getCurrentUserDetails();
        if (bufferUserDetails != null) {
            String userName = bufferUserDetails.getUsername();
            result = userService.getByName(userName);
        }
        return result;
    }

}