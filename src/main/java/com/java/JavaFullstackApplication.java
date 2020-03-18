package com.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.services.S3Service;

@SpringBootApplication
public class JavaFullstackApplication implements CommandLineRunner{
	
	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(JavaFullstackApplication.class, args);
	}		
	
	@Override
	public void run(String... args) throws Exception {
		
		s3Service.uploadFile("C:\\Users\\felli\\Documents\\teste.txt");
		
	}
}
