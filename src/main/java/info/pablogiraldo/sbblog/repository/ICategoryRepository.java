package info.pablogiraldo.sbblog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import info.pablogiraldo.sbblog.entity.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

	Page<Category> findAllByOrderByIdDesc(Pageable categoryPageable);

	Category findByName(String name);
}
