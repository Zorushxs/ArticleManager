package com.korber.task.web;

import com.korber.task.api.ManagerApi;
import com.korber.task.api.domain.Article;
import com.korber.task.service.ArticleManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Slf4j
@AllArgsConstructor
@Controller
public class ManagerController implements ManagerApi {

    @Autowired
    private ArticleManager articleManager;

    @Override
    public ResponseEntity<Article> createArticle(Article article) {

        Article createdArticle = articleManager.createArticle(article);
        if (createdArticle != null) {

            log.debug("Conroller - Article created: {}", createdArticle);
            return ResponseEntity.ok(createdArticle);
        }

        log.debug("Controller - Article not created: {}", article);
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Article> getArticle(String id) {

        Article article = articleManager.getArticle(id);
        if (article != null) {

            log.debug("Controller - Article found: {}", article);
            return ResponseEntity.ok(article);
        }

        log.debug("Controller - Article not found: {}", id);
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Article> updateArticle(String id, Article article) {

        Article updatedArticle = articleManager.updateArticle(id, article);
        if (updatedArticle != null) {

            log.debug("Controller - Article updated: {}", updatedArticle);
            return ResponseEntity.ok(updatedArticle);
        }

        log.debug("Controller - Article not updated: {}", article);
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Void> deleteArticle(String id) {

        Article deletedArticle = articleManager.deleteArticle(id);

        if (deletedArticle != null) {

            log.debug("Controller - Article deleted: {}", id);
            return ResponseEntity.ok().build();
        }

        log.debug("Controller - Article not deleted: {}", id);
        return ResponseEntity.badRequest().build();
    }
}
