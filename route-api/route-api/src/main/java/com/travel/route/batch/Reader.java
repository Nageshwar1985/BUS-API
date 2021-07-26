package com.travel.route.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import com.travel.route.model.Bus;


public class Reader extends FlatFileItemReader<Bus> {
	
	public Reader(Resource resource) {
		
		super();
		
		setResource(resource);
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "id", "busNo", "busType", "seatCount" });
		lineTokenizer.setDelimiter(",");
	    lineTokenizer.setStrict(false);
	    
	    BeanWrapperFieldSetMapper<Bus> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Bus.class);

		DefaultLineMapper<Bus> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		setLineMapper(defaultLineMapper);
	}
}
