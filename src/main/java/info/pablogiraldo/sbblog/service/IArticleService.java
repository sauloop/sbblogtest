package info.pablogiraldo.sbblog.service;

import java.util.Optional;

//import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import info.pablogiraldo.sbblog.entity.Article;

public interface IArticleService {

	public Iterable<Article> listArticles();

	public Page<Article> listArticles(Pageable articlePageable);

	public void addArticle(Article article);

	public Optional<Article> findArticleById(long id);

	public void deleteArticle(long id);
}
