package com.travel.route.batch;

import java.io.File;
import java.net.URL;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.travel.route.model.Bus;


@Component
public class BusAddtionsJob extends JobExecutionListenerSupport {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${input.file}") 
	Resource resource;
	
	@Autowired
	Processor processor;
	
	@Autowired
	Writer writer;
	
	@Bean(name = "busadditionsJob")
	public Job busadditionsJob() {
		
//		 String fileName = "config/sample.txt";
//	        ClassLoader classLoader = getClass().getClassLoader();
//	 
//	        File file = new File(classLoader.getResource(fileName).getFile());
//	        Resource resource1 = new Resource(file);
		Resource[]  resource1  = new Resource[] {new FileSystemResource("C:\\Workspace\\Yourbus\\route-api\\route-api\\src\\main\\resources\\bus-data.csv")};
		

		Step step = stepBuilderFactory.get("step-1")
				.<Bus, Bus> chunk(2)
				.reader(new Reader(resource1[0]))
				.processor(processor)
				.writer(writer)
				.build();
		
		Job job = jobBuilderFactory.get("busaddtions-job")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();

		return job;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}
