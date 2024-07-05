package com.korber.task.web;

import com.korber.task.api.domain.Article;
import com.korber.task.service.ArticleManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ManagerControllerTest {

    @Mock
    private ArticleManager articleManager;

    @InjectMocks
    private ManagerController managerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();
    }

    @Test
    void testCreateArticle() throws Exception {
        Article article = new Article().setDescription("Test Article").setWeight(10.0).setVolume(5.0);
        when(articleManager.createArticle(any(Article.class))).thenReturn(article);

        mockMvc.perform(post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Test Article\",\"weight\":10.0,\"volume\":5.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Test Article"))
                .andExpect(jsonPath("$.weight").value(10.0))
                .andExpect(jsonPath("$.volume").value(5.0));

        verify(articleManager, times(1)).createArticle(any(Article.class));
    }

    @Test
    public void testGetArticle() throws Exception {
        // Arrange
        Article article = new Article()
                .setId("1")
                .setDescription("A sample article")
                .setWeight(1.2)
                .setVolume(3.4)
                .setIsActive(true);

        when(articleManager.getArticle(anyString())).thenReturn(article);

        // Act & Assert
        mockMvc.perform(get("/articles/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"1\",\"description\":\"A sample article\",\"weight\":1.2,\"volume\":3.4,\"isActive\":true}"));
    }

    @Test
    public void testGetArticleNotFound() throws Exception {
        // Arrange
        when(articleManager.getArticle(anyString())).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/articles/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateArticle() throws Exception {
        Article article = new Article().setDescription("Updated Article").setWeight(12.0).setVolume(6.0);
        when(articleManager.updateArticle(anyString(), any(Article.class))).thenReturn(article);

        mockMvc.perform(put("/articles/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Updated Article\",\"weight\":12.0,\"volume\":6.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated Article"))
                .andExpect(jsonPath("$.weight").value(12.0))
                .andExpect(jsonPath("$.volume").value(6.0));

        verify(articleManager, times(1)).updateArticle(anyString(), any(Article.class));
    }

    @Test
    void testDeleteArticle() throws Exception {
        doNothing().when(articleManager).deleteArticle(anyString());

        mockMvc.perform(delete("/articles/{id}", "1"))
                .andExpect(status().isOk());

        verify(articleManager, times(1)).deleteArticle(anyString());
    }
}
