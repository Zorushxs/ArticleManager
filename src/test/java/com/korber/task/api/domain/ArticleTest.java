package com.korber.task.api.domain;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @Test
    public void testArticle() {
        // Verify that the Article class has a valid constructor
        MatcherAssert.assertThat(Article.class, hasValidBeanConstructor());

        // Verify that the Article class has valid getters and setters
        MatcherAssert.assertThat(Article.class, hasValidGettersAndSetters());
    }

}