package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.pojos.Category;
import com.app.pojos.Product;

public interface CategoryService {

	List <Category > getAllCategories();

    Product addProductsByCategory(long id,Product product);

	List<Product> fetchProductsByCategory(long id);
	
	Category getCategoryById(Long catId);

	Category addNewCategory(Category newCategory);

	Category updateCategory(Category existCategory);

	String removeCategoryById(Long catId);

}
