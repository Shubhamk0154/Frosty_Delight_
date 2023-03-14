package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ProductAddDTO;
import com.app.pojos.Category;
import com.app.pojos.Product;
import com.app.service.CategoryService;
import com.app.service.ImageHandlingService;
import com.app.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")

public class ProductController 

{
	 
	@Autowired
	private ImageHandlingService imageService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService catService;
	
	@GetMapping
	public ResponseEntity<?> getAllProducts()
	{
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@GetMapping("cat/{id}")
	public ResponseEntity<?> getAllProductsByCategoryId(@PathVariable Long id)
	{
		return ResponseEntity.ok(productService.getAllByCategoriesId(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id)
	{
		return ResponseEntity.ok(productService.getProductById(id));
	}
	
	@PostMapping("/{catId}")
	public ResponseEntity<?> saveNewProduct(@RequestBody ProductAddDTO newProduct
			,@PathVariable Long catId)
	{
		Category category = catService.getCategoryById(catId);
		newProduct.setProductCategory(category);
		return ResponseEntity.ok(productService.addNewProduct(newProduct));
	}
	
	@PatchMapping
	public ResponseEntity<?> updateProduct(@RequestBody Product existProduct)
	{
		return ResponseEntity.ok(productService.updateProduct(existProduct));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeProductById(@PathVariable Long id)
	{
		return ResponseEntity.ok(productService.removeProduct(id));
	}
	
	@PostMapping(value="/{id}/image",consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImageToServerSideFolder(@RequestParam MultipartFile imageFile,
			@PathVariable Long id
			) throws IOException {
		System.out.println("in upload img " + id + " " + imageFile.getOriginalFilename());
		return new ResponseEntity<>(imageService.uploadImage(id, imageFile), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}/image", produces = { MediaType.IMAGE_GIF_VALUE, 
			MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> serveImageFromServerSideFolder(@PathVariable Long id) throws IOException
	{
		return new ResponseEntity<>(imageService.serveImage(id),HttpStatus.OK);
	}
}
