package info.pablogiraldo.sbblog.service;

import java.util.List;

import info.pablogiraldo.sbblog.entity.Article;

public interface IArticleService {

	public Iterable<Article> listArticles();

	public void addArticle(Article article);
}
