package com.travel.route.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
@RestController
public class BatchJobController {
 
	private static final String DIR_TO_UPLOAD = "C:\\Workspace\\Yourbus\\route-api\\route-api\\src\\main\\resources\\";
	
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    @Qualifier("busadditionsJob")
    Job busadditionsJob;
    

	@PostMapping("/upload/buses")
	public String uploadToDirectory(@RequestParam MultipartFile file) throws Exception, IOException {

		byte[] bytes = file.getBytes();
		Path path = Paths.get(DIR_TO_UPLOAD + file.getOriginalFilename());
		Files.write(path, bytes);
		
		JobParametersBuilder builder = new JobParametersBuilder();
		 builder.addDate("date", new Date())
        .addLong("time",System.currentTimeMillis()).toJobParameters();
		
		jobLauncher.run(busadditionsJob, builder.toJobParameters());

		return "Buslist is getting updated through Batch job";

	}
}