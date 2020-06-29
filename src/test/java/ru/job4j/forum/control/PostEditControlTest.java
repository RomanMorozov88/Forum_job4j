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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.postservices.PostService;
import ru.job4j.forum.service.postservices.PostServiceRepo;
import ru.job4j.forum.service.repositories.PostRepository;
import ru.job4j.forum.service.utils.GetMainUserUtil;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class, properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
class PostEditControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageGet() throws Exception {
        this.mockMvc.perform(get("/edit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @MockBean
    private PostRepository postRepository;
    /**
     * Что бы избежать NullPointException при проверке автора\текущего пользователя
     * в PostEditControl.
     */
    @MockBean
    private GetMainUserUtil getMainUserUtil;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessagePost() throws Exception {
        when(getMainUserUtil.getCurrentUser()).thenReturn(new User());
        this.mockMvc.perform(post("/edit")
                .param("name", "Куплю ладу-грант. Дорого.")
                .param("description", "Куплю ладу-грант. Дорого. Описание.")
                .param("id", "-1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(postRepository).save(argument.capture());
        Assert.assertEquals("Куплю ладу-грант. Дорого.", argument.getValue().getName());
        Assert.assertEquals("Куплю ладу-грант. Дорого. Описание.", argument.getValue().getDescription());
    }

    /**
     * Т.к. в AppConfig может использоваться другая реализация PostService, то
     * для возможности переопределения userService из этой тестовой конфигурации
     * определяем @SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
     * Здесь для тестирования используется PostServiceRepo.
     */
    @TestConfiguration
    static class TestAppConfig {
        @Bean
        public PostService postService() {
            return new PostServiceRepo();
        }
    }

}