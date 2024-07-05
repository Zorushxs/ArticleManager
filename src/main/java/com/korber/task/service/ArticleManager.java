package com.korber.task.service;

import com.korber.task.api.domain.Article;

public interface ArticleManager {

    Article createArticle(Article article);
    Article getArticle(String id);
    Article updateArticle(String id, Article article);
    Article deleteArticle(String id);

}
