package dev.sgwrth.orderlogger.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.sgwrth.orderlogger.dto.KeyValuePairDto;
import dev.sgwrth.orderlogger.entity.Article;
import dev.sgwrth.orderlogger.repository.ArticleRepository;

public interface ArticleService {

	@Service
	class ArticleServiceImpl implements ArticleService {
		
		private ArticleRepository articleRepository;
		
		ArticleServiceImpl(ArticleRepository articleRepository) {
			this.articleRepository = articleRepository;
		}
		
		@Override
		public List<Article> getArticles() {
			return this.articleRepository.findAll();
		}
		
		@Override
		public List<Article> getArticlesByAttribute(KeyValuePairDto kvp) {
			return this.articleRepository.getArticlesByAttribute(kvp.key(), kvp.value());
		}
	}
	
	List<Article> getArticles();
	
	List<Article> getArticlesByAttribute(KeyValuePairDto kvp);

}
