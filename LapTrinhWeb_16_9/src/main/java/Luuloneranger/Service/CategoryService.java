package Luuloneranger.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import Luuloneranger.Entity.Category;

public interface CategoryService {
	List<Category> findAll();
	Page<Category> findAll(Pageable pageable);
	List<Category> findAll(Sort sort);
	
	Optional<Category> findById(int id);
	Category  save (Category entity);
	
	<S extends Category> Optional<S> findOne(Example<S> example);
	long count();
	void deleteById(int id);
	void delete(Category entity);
	void deleteAll();
    List<Category> findByCategoryNameContaining(String name);
    Page<Category> findByCategoryNameContaining(String name, Pageable pageable);
}
