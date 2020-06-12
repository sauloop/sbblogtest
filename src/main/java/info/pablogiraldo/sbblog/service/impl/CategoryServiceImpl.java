package info.pablogiraldo.sbblog.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import info.pablogiraldo.sbblog.entity.Category;
import info.pablogiraldo.sbblog.repository.ICategoryRepository;
import info.pablogiraldo.sbblog.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public Iterable<Category> listCategories() {

		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> listCategories(Pageable categoryPageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCategory(Category category) {
		categoryRepository.save(category);

	}

	@Override
	public Optional<Category> findCategoryById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategory(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Category findCategoryByName(String name) {

		return categoryRepository.findByName(name);
	}

}
