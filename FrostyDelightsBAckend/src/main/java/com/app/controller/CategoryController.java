package com.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Category;
import com.app.pojos.Product;
import com.app.service.CategoryService;
import com.app.service.ImageHandlingService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/category")

public class CategoryController {
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private ImageHandlingService imageService;
	
	@GetMapping
	public ResponseEntity<?> getAllCategories()
	{
		return ResponseEntity.ok(catService.getAllCategories());
	}
	
	
	@PostMapping("/addProducts/{id}")
	public ResponseEntity<?> addProductByCategory(@PathVariable("id") long id,@RequestBody Product product)
	
	{
		return ResponseEntity.ok(catService.addProductsByCategory(id,product));

     }
	
	
	@PostMapping(value = "/{catId}/image_upload", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImageToServerSideFolder(@PathVariable Long catId,
			@RequestParam MultipartFile imageFile) throws IOException{
		System.out.println("in upload img " + catId + " " + imageFile.getOriginalFilename());
		return new ResponseEntity<>(imageService.uploadImage(catId, imageFile), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{catId}/image", produces = { MediaType.IMAGE_GIF_VALUE, 
 			MediaType.IMAGE_JPEG_VALUE,
 			MediaType.IMAGE_PNG_VALUE })
 	public ResponseEntity<?> serveImageFromServerSideFolder(@PathVariable Long catId) throws IOException {
 		System.out.println("in serve img " + catId);
 		return new ResponseEntity<>(imageService.serveImage(catId), HttpStatus.OK);
 	}
	
	@GetMapping("{id}/products")
	public List<Product> showProductsByCategory(@PathVariable long id)
	{
		return catService.fetchProductsByCategory(id);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long id)
	{
		return ResponseEntity.ok(catService.getCategoryById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> saveNewCategory(@RequestBody Category newCategory)
	{
		return ResponseEntity.ok(catService.addNewCategory(newCategory));
	}
	
	@PutMapping
	public ResponseEntity<?> updateCategory(@RequestBody Category existCategories)
	{
		return ResponseEntity.ok(catService.updateCategory(existCategories));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeCategory(@PathVariable Long id)
	{
		return ResponseEntity.ok(catService.removeCategoryById(id));
	}
	
	

}
