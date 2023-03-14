package com.app.service;

import java.util.List;

import com.app.dto.ProductAddDTO;
import com.app.pojos.Product;

public interface ProductService {

	Product findproductById(Long id);

	List<Product> getAllProducts();

	List<Product> getAllByCategoriesId(Long id);

	Product getProductById(Long id);

	String addNewProduct(ProductAddDTO newProduct);

	Product updateProduct(Product existProduct);

	String removeProduct(Long id);

}
