package com.korber.task.service.impl;

import com.korber.task.api.domain.Article;
import com.korber.task.service.ArticleManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class ArticleManagerImpl implements ArticleManager {

    private Map<String, Article> articles = new HashMap<>();

    @Override
    public Article createArticle(Article article) {

        String id = UUID.randomUUID().toString();
        article.setId(id);
        article.setDescription(article.getDescription());
        article.setWeight(article.getWeight());
        article.setVolume(article.getVolume());
        article.setIsActive(true);
        log.debug("Service - Article created: {}", article);

        articles.put(article.getId(), article);

        return article;
    }

    @Override
    public Article getArticle(String id) {

        if (articles.containsKey(id)) {
            return articles.get(id);
        }

        log.debug("Service - Article not found: {}", id);
        return null;
    }

    @Override
    public Article updateArticle(String id, Article article) {

        if (articles.containsKey(id)) {
            article.setId(id);
            articles.put(id, article);
            log.debug("Service - Article updated: {}", article);

            return article;
        }

        log.debug("Service - Article not found (cannot update): {}", id);
        return null;
    }

    @Override
    public Article deleteArticle(String id) {

        if (articles.containsKey(id)) {
            Article article = articles.remove(id);
            return article;
        } else {
            log.debug("Service - Article not found (cannot remove): {}", id);
            return null;
        }
    }
}
