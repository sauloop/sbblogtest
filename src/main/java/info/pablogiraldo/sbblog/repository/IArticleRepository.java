package info.pablogiraldo.sbblog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import info.pablogiraldo.sbblog.entity.Article;

@Repository
public interface IArticleRepository extends CrudRepository<Article, Long> {
	public Iterable<Article> findAllByOrderByIdDesc();
}
