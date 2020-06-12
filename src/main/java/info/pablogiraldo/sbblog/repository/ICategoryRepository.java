package info.pablogiraldo.sbblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.pablogiraldo.sbblog.entity.Article;
import info.pablogiraldo.sbblog.entity.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);
}
