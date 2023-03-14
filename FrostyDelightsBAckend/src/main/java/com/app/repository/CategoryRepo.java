package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Category;


public interface CategoryRepo extends JpaRepository<Category, Long> {
	
	Category findById(long id);

	Category getCategoryById(long id);

	Optional<Category> findCategoryById(Long catId);

}
