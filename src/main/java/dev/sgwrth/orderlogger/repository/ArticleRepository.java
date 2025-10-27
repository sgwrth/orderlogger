package dev.sgwrth.orderlogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sgwrth.orderlogger.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
