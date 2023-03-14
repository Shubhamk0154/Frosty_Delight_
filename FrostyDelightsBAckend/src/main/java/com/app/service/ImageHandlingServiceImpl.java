package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.CustomException.ResourceNotFoundException;
import com.app.dto.ApiResponse;

import com.app.pojos.Category;
import com.app.repository.CategoryRepo;


@Service
@Transactional
public class ImageHandlingServiceImpl implements ImageHandlingService {
	// To tell SC , evaluate SpEL (spring expr language) n inject it's value -->
	// field
	@Value("${content.upload.folder}")
	private String folderName;
	// dep : dao layer i/f :
	@Autowired
	private CategoryRepo catRepo;

	@PostConstruct
	public void myInit() {
		System.out.println("in myInit " + folderName);
		// chk of folder exists --o.w create one!
		File path = new File(folderName);
		if (!path.exists()) {
			path.mkdirs();
		} else
			System.out.println("folder alrdy exists....");
	}

	@Override
	public ApiResponse uploadImage(Long catId, MultipartFile imageFile) throws IOException{
		// chk if emp exists by the id ?
		Category category = catRepo.findById(catId)
				.orElseThrow();
		// valid emp : PERSISTENT --create complete path to the image
	//	Path targetPath=Paths.get(new File(folderName).getAbsolutePath()+ File.separator + imageFile.getOriginalFilename());
		String targetPath=folderName+File.separator+imageFile.getOriginalFilename();
		
		System.out.println(targetPath);
		//copy image file contents to the specified path
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		//=> success
		//save image path in DB
		category.setImageUrl(targetPath);
		return new ApiResponse("Image Uploaded successfully!");
	}

	@Override
	public byte[] serveImage(Long catId) throws IOException {
		// chk if product exists by the id ?
		Category category = catRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Invalid category Id : Image Download failed!!!!"));
				//.orElseThrow(() -> new ResourceNotFoundException("Invalid Product Id : Image Download failed!!!!!!);
		// valid product : PERSISTENT --create complete path to the image
		String path = category.getImageUrl();
		if (path == null)
			throw new ResourceNotFoundException("Image does not exist !!!!!");
		// OR to lift it from DB product.getContents() --> byte[]
		return Files.readAllBytes(Paths.get(path));

	}

}
