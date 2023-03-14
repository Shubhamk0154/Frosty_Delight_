package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.CustomException.ResourceNotFoundException;
import com.app.dto.ProductAddDTO;
import com.app.pojos.Category;
import com.app.pojos.Product;
import com.app.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CategoryService catService;
	@Override
	public Product findproductById(Long id) {
		
		Product product = productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No products"));
		
		return product;
	}
	@Override
	public List<Product> getAllProducts() {
		
		return productRepo.findAll();
	}
	@Override
	public List<Product> getAllByCategoriesId(Long id) {
		Category category = catService.getCategoryById(id);
		return productRepo.findAllByproductCategory(category);
	}
	@Override
	public Product getProductById(Long id) {
		return productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Product ID !!!!!"));
	}
	@Override
	public String addNewProduct(ProductAddDTO newProduct) {
		Product product=mapper.map(newProduct, Product.class);
		productRepo.save(product);
		return "New Product is added with Id : "+product.getId();
	}
	
	@Override
	public Product updateProduct(Product existProduct) {
		if (productRepo.existsById(existProduct.getId())) 
		{
			return productRepo.save(existProduct);
		}
		throw new ResourceNotFoundException("Invalid Product Id : Updation Failed!!!!!!!!");
	}
	@Override
	public String removeProduct(Long id) {
		if (productRepo.existsById(id)) 
		{
			productRepo.deleteById(id);
			return "Product deleted with productId : "+id;
		}
		return "Deletion Failed Invalid Id !!!!!";
	}

}
