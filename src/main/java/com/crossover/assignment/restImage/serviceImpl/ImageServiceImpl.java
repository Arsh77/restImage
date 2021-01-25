package com.crossover.assignment.restImage.serviceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.crossover.assignment.restImage.entity.ImageDetails;
import com.crossover.assignment.restImage.repository.ImageDetailsRepository;
import com.crossover.assignment.restImage.service.ImageService;
import com.crossover.assignment.restImage.service.S3Service;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageDetailsRepository imRepo;

	@Autowired
	S3Service s3Service;

	@Override
	public ResponseEntity<Object> uploadImage(MultipartFile image, float size, String fileName, String fileExtension,
			String description) throws FileNotFoundException, IOException, AmazonServiceException {
		
		ImageDetails imageDetails = new ImageDetails();
		imageDetails.setSize(size);
		imageDetails.setExtension(fileExtension);
		imageDetails.setDescription(description);
		imageDetails.setName(fileName);
		imRepo.save(imageDetails);

		s3Service.s3upload("" + imRepo.findTopByOrderByIdDesc().getId(), image);

		return new ResponseEntity<>("image upoaded successfully", HttpStatus.CREATED);
	}
}
