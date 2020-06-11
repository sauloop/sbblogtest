package info.pablogiraldo.sbblog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import info.pablogiraldo.sbblog.entity.Article;

@Repository
public interface IArticleRepository extends CrudRepository<Article, Long> {
	public Iterable<Article> findAllByOrderByIdDesc();

	public Page<Article> findAllByOrderByIdDesc(Pageable articlePageable);

//	@EntityGraph(value = "Category.articles", type = EntityGraphType.FETCH)
//	List<Article> findByCategoryId(int category);
}
