package com.crossover.assignment.restImage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crossover.assignment.restImage.entity.ImageDetails;

public interface ImageDetailsRepository extends JpaRepository<ImageDetails, Integer>{
	
	public ImageDetails findTopByOrderByIdDesc();
}
