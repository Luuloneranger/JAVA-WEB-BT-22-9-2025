package Luuloneranger.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Luuloneranger.Entity.Category;
import Luuloneranger.Repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	

	@Override
	public List<Category> findByCategoryNameContaining(String name) {
		return categoryRepository.findByCategoryNameContaining(name);
	}

	@Override
	public Page<Category> findByCategoryNameContaining(String name, Pageable pageable) {
		return categoryRepository.findByCategoryNameContaining(name, pageable);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}



	@Override
	public List<Category> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}


	@Override
	public Optional<Category> findById(int id) {
		return categoryRepository.findById(id);
	}



	@Override
	public <S extends Category> Optional<S> findOne(Example<S> example) {
		return categoryRepository.findOne(example);
	}



	@Override
	public long count() {
		return categoryRepository.count();
	}



	@Override
	public void deleteById(int id) {
		categoryRepository.deleteById(id);
	}



	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
		
	}



	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
		
	}



	@Override
	public Category  save(Category entity) {
			return categoryRepository.save(entity);
	}
	
	

}
