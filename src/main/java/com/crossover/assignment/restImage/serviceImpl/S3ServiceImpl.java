package com.crossover.assignment.restImage.serviceImpl;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.crossover.assignment.restImage.service.S3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

@Component
public class S3ServiceImpl implements S3Service {

    private AmazonS3 s3client;

    @Value("${aws.access.key.id}")
    private String accessKey;

    @Value("${aws.access.key.secret}")
    private String secretKey;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.s3.audio.bucket}")
    private String bucketName;

    private Logger logger; 
    
    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(awsRegion).build();
        this.logger = Logger.getLogger(S3ServiceImpl.class.getName());
    }
	
    @Override
	public void s3upload(String fileName, MultipartFile image)
			throws FileNotFoundException, IOException, AmazonServiceException {

		File file = convertMultiPartToFile(image);
		s3client.putObject(new PutObjectRequest(bucketName, fileName, file));
		logger.info(image.getOriginalFilename()+" named image is uploaded to s3 with name "+ fileName);
		file.delete();
	}
	
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
