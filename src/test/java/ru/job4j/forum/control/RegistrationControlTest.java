package ru.job4j.forum.control;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.repositories.UserRepository;
import ru.job4j.forum.service.userservices.UserService;
import ru.job4j.forum.service.userservices.UserServiceRepo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class, properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
class RegistrationControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessageGet() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @MockBean
    private UserRepository userRepository;
    /**
     * Подменяем процесс кодировки пароля при сохранении пользователя в userService.
     */
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldReturnDefaultMessagePost() throws Exception {
        String testPassword = "123";
        when(passwordEncoder.encode(testPassword)).thenReturn(testPassword);
        this.mockMvc.perform(post("/reg")
                .param("name", "Test Name")
                .param("password", testPassword)
                .param("roles", "ROLE_USER"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argument.capture());
        Assert.assertEquals("Test Name", argument.getValue().getName());
        Assert.assertEquals(testPassword, argument.getValue().getPassword());
    }

    /**
     * Т.к. в AppConfig может использоваться другая реализация UserService, то
     * для возможности переопределения userService из этой тестовой конфигурации
     * определяем @SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
     * Здесь для тестирования используется UserServiceRepo.
     */
    @TestConfiguration
    static class TestAppConfig {
        @Bean
        public UserService userService() {
            return new UserServiceRepo();
        }
    }

}