package com.korber.task.api;

import com.korber.task.api.domain.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/articles")
public interface ManagerApi {

    @PostMapping
    ResponseEntity<Article> createArticle(@RequestBody Article article);

    @GetMapping("/{id}")
    ResponseEntity<Article> getArticle(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<Article> updateArticle(@PathVariable String id, @RequestBody Article article);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteArticle(@PathVariable String id);
}
