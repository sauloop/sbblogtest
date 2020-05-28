package info.pablogiraldo.sbblog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.pablogiraldo.sbblog.entity.Article;

@Repository
public interface IArticleJpaRepository extends JpaRepository<Article, Long> {
	public Page<Article> findAllByOrderByIdDesc(Pageable articlePageable);
}
