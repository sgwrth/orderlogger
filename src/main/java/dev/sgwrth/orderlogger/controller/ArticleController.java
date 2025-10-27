package dev.sgwrth.orderlogger.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sgwrth.orderlogger.entity.Article;
import dev.sgwrth.orderlogger.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {

	private ArticleService articleService;
	
	ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@GetMapping("/all")
	private List<Article> getArticles() {
		return this.articleService.getArticles();
	}
}
