package com.crossover.assignment.restImage.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.crossover.assignment.restImage.exception.ImproperRequestException;
import com.crossover.assignment.restImage.serviceImpl.ImageServiceImpl;

@RestController()
public class Controller {
	@Autowired
	ImageServiceImpl service;

	/*
	 * @Autowired S3Service amazonS3ClientService;
	 */

	@RequestMapping(value = "/image", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Transactional
	public ResponseEntity<Object> uploadImage(@RequestParam(required = true, value = "description") String description,
			@RequestParam(required = true, value = "image") MultipartFile image)
			throws IOException, AmazonServiceException {

		float size = (float) (image.getSize() / 1024.0);
		String fileName = image.getOriginalFilename();
		String fileExtension = fileName.substring(fileName.length() - 3);

		if (size > 500.0) {
			throw new ImproperRequestException("Size of image cannot exceed 500kb");
		}
		if (!(fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("png"))) {
			throw new ImproperRequestException("Only png and jpg image are allowed");
		}
		ResponseEntity<Object> response = this.service.uploadImage(image, size, fileName, fileExtension, description);
		return response;
	}

	/*
	 * @RequestMapping(value = "/deleteimage", method = RequestMethod.DELETE) public
	 * ResponseEntity<Object> deleteFile(@RequestParam("file_name") String fileName)
	 * { this.amazonS3ClientService.deleteFile(fileName);
	 * 
	 * return new ResponseEntity<>(fileName + " deleted succeffsully",
	 * HttpStatus.OK); }
	 */
}
