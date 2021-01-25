package com.crossover.assignment.restImage.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;

public interface S3Service {

	public void s3upload(String fileName, MultipartFile image)
			throws FileNotFoundException, IOException, AmazonServiceException;


}
