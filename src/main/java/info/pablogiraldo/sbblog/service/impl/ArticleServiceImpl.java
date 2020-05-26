package info.pablogiraldo.sbblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

//		return articleRepository.findAll();

		return articleRepository.findAllByOrderByIdDesc();
	}

	@Override
	public void addArticle(Article article) {
		articleRepository.save(article);

	}

}
