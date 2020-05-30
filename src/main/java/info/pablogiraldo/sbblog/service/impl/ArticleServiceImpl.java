package info.pablogiraldo.sbblog.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import info.pablogiraldo.sbblog.entity.Article;
import info.pablogiraldo.sbblog.repository.IArticleRepository;
import info.pablogiraldo.sbblog.service.IArticleService;

@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private IArticleRepository articleRepository;

	@Override
	public Iterable<Article> listArticles() {

		return articleRepository.findAllByOrderByIdDesc();
	}

	@Override
	public Page<Article> listArticles(Pageable articlePageable) {

		return articleRepository.findAllByOrderByIdDesc(articlePageable);

	}

	@Override
	public void addArticle(Article article) {
		articleRepository.save(article);

	}

	@Override
	public Optional<Article> findArticleById(long id) {
		return articleRepository.findById(id);
	}

	@Override
	public void deleteArticle(long id) {
		articleRepository.deleteById(id);
	}

}
