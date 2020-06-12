package info.pablogiraldo.sbblog.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import info.pablogiraldo.sbblog.entity.Category;

public interface ICategoryService {

	Iterable<Category> listCategories();

	Page<Category> listCategories(Pageable categoryPageable);

	void addCategory(Category category);

	Optional<Category> findCategoryById(long id);

	void deleteCategory(long id);

	Category findCategoryByName(String name);

}
