package ru.job4j.forum.control;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.job4j.forum.Main;
import ru.job4j.forum.service.postservices.PostService;
import ru.job4j.forum.service.utils.GetMainUserUtil;

import java.util.ArrayList;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class IndexControlTest {

    private MockMvc mockMvc;

    @Mock
    private PostService postService;
    @Mock
    private GetMainUserUtil getMainUserUtil;

    @InjectMocks
    private IndexControl indexControl;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(indexControl)
                .build();
        when(postService.getAll()).thenReturn(new ArrayList<>());
        when(getMainUserUtil.getCurrentUserDetails()).thenReturn(null);
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}