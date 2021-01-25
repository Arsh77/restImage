package com.crossover.assignment.restImage.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;

public interface ImageService {
	
	public ResponseEntity<Object> uploadImage(MultipartFile image, float size, String fileName, String fileExtension,
			String description) throws FileNotFoundException, IOException, AmazonServiceException;

}
