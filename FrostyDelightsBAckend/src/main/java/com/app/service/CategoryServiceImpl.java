package com.app.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.CustomException.ResourceNotFoundException;
import com.app.pojos.Category;
import com.app.pojos.Product;
import com.app.repository.CategoryRepo;
import com.app.repository.ProductRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductRepository ProductRepo;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public Product addProductsByCategory(long id,Product product) {
		
		Category category = categoryRepo.findById(id);
		
		category.addProduct(product);
		
		 return ProductRepo.save(product);
		
	}

	@Override
	public List<Product> fetchProductsByCategory(long id) {
		
		Category category = categoryRepo.getCategoryById(id);
		return ProductRepo.findAllByproductCategory(category);
		
	}

	
	@Override
	public Category getCategoryById(Long catId) {
		return categoryRepo.findCategoryById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Product ID !!!!!"));
	}

	@Override
	public Category addNewCategory(Category newCategory) {
		// TODO Auto-generated method stub
		return categoryRepo.save(newCategory);
	}

	@Override
	public Category updateCategory(Category existCategory) {
		if(categoryRepo.existsById(existCategory.getId())) {
			return categoryRepo.save(existCategory);
		}
		throw new ResourceNotFoundException("Invalid Category Id : Updation Failed!!!!!!!!");
	}

	@Override
	public String removeCategoryById(Long catId) {
		if(categoryRepo.existsById(catId)) {
			 categoryRepo.deleteById(catId);
			 return "Deleted";
		}
		return "Deletion Failed Invalid Id !!!!!";
		}
	
}
	
	

	
	 
	

