package com.korber.task.service.impl;

import com.korber.task.api.domain.Article;
import com.korber.task.service.ArticleManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleManagerImplTest {

    private ArticleManager articleManager;

    @BeforeEach
    public void setUp() {
        articleManager = new ArticleManagerImpl(new HashMap<>());
    }

    @Test
    public void testCreateArticle() {
        Article article = new Article()
                .setDescription("Test Article")
                .setWeight(10.0)
                .setVolume(5.0);

        Article createdArticle = articleManager.createArticle(article);

        assertNotNull(createdArticle.getId());
        assertEquals("Test Article", createdArticle.getDescription());
        assertEquals(10.0, createdArticle.getWeight());
        assertEquals(5.0, createdArticle.getVolume());
        assertTrue(createdArticle.getIsActive());
    }

    @Test
    public void testGetArticleWhenArticleExists() {
        // Arrange
        Article article = new Article()
                .setId("1")
                .setDescription("A sample article")
                .setWeight(1.2)
                .setVolume(3.4)
                .setIsActive(true);

        articleManager.createArticle(article);
        String id = article.getId();

        // Act
        Article foundArticle = articleManager.getArticle(id);

        // Assert
        assertNotNull(foundArticle);
        assertEquals(id, foundArticle.getId());
        assertEquals("A sample article", foundArticle.getDescription());
        assertEquals(1.2, foundArticle.getWeight());
        assertEquals(3.4, foundArticle.getVolume());
        assertTrue(foundArticle.getIsActive());
    }

    @Test
    public void testGetArticleWhenArticleDoesNotExist() {
        // Act
        Article foundArticle = articleManager.getArticle("2");

        // Assert
        assertNull(foundArticle);
    }

    @Test
    public void testUpdateArticle() {
        Article article = new Article()
                .setDescription("Test Article")
                .setWeight(10.0)
                .setVolume(5.0)
                .setIsActive(true);

        Article createdArticle = articleManager.createArticle(article);
        createdArticle.setDescription("Updated Article");

        Article updatedArticle = articleManager.updateArticle(createdArticle.getId(), createdArticle);

        assertNotNull(updatedArticle);
        assertEquals("Updated Article", updatedArticle.getDescription());
    }

    @Test
    public void testDeleteArticle() {
        // Arrange
        Article article = new Article()
                .setDescription("Test Article")
                .setWeight(10.0)
                .setVolume(5.0)
                .setIsActive(true);

        Article createdArticle = articleManager.createArticle(article);

        // Act
        Article deletedArticle = articleManager.deleteArticle(createdArticle.getId());

        // Assert
        assertNotNull(deletedArticle);
        assertEquals(createdArticle.getId(), deletedArticle.getId());
        assertEquals("Test Article", deletedArticle.getDescription());
        assertEquals(10.0, deletedArticle.getWeight());
        assertEquals(5.0, deletedArticle.getVolume());
        assertTrue(deletedArticle.getIsActive());

        // Verify article no longer exists in manager
        assertNull(articleManager.getArticle(createdArticle.getId()));
    }

}
