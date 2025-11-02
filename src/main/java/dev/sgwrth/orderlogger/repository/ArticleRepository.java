package dev.sgwrth.orderlogger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sgwrth.orderlogger.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	@Query(
			value = """
					SELECT *
					FROM articles a
					WHERE a.attributes ->> ?1 = ?2
					""",
			nativeQuery = true
	)
	List<Article> getArticlesByAttribute(String key, String value);

}
