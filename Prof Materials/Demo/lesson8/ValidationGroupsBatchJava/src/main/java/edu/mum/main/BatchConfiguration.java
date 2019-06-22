package edu.mum.main;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.scheduling.annotation.EnableScheduling;

import edu.mum.batch.Authenticate;
import edu.mum.batch.ProductItemWriter;
import edu.mum.domain.Product;
import edu.mum.service.ProductService;

@Configuration
//modular = true for modularizing your configuration if there are multiple jobs.
@EnableBatchProcessing(modular = false)  
@EnableScheduling
public class BatchConfiguration {
	
    @Autowired
    private JobBuilderFactory jobBuilders;
 
    @Autowired
    private StepBuilderFactory stepBuilders;

    @Autowired
    ProductService productService;
    
	@Value("org/springframework/batch/core/schema-drop-mysql.sql")
	private Resource dropSchemaScript;

	@Value("org/springframework/batch/core/schema-mysql.sql")
	private Resource schemaScript;

	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
	    final DataSourceInitializer initializer = new DataSourceInitializer();
	    initializer.setDataSource(dataSource);
	    initializer.setDatabasePopulator(databasePopulator());
	    return initializer;
	}

	private DatabasePopulator databasePopulator() {
	    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
// create job-meta tables 
	    populator.addScript(dropSchemaScript);
	    populator.addScript(schemaScript);
	    return populator;
	}
	
	@Bean
	public FlatFileItemReader<Product> reader(){
		FlatFileItemReader<Product> itemReader = new FlatFileItemReader<Product>();
		itemReader.setLineMapper(lineMapper());
		itemReader.setResource(new ClassPathResource("data/products.csv"));
		return itemReader;
	}
 
	@Bean
	public LineMapper<Product> lineMapper(){
		DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<Product>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setNames(new String[]{"name","manufacturer","weight","description","shortDescription",
				                                 "productNumber","price","quantity"});
 		BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<Product>();
		fieldSetMapper.setTargetType(Product.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
 
	@Bean
	public ItemWriter<Product> writer(){
		ProductItemWriter itemWriter = new ProductItemWriter();
		itemWriter.setProductService(productService);
		return itemWriter;
	}

	@Bean
	public Authenticate authenticate(){
		Authenticate authenticate = new Authenticate();
		return authenticate;
	}

	@Bean
	public Job saveProducts(){
		return jobBuilders.get("saveProducts")
 				.start(step1Authenticate())
				.next(step2ETL())
				.build();
	}
 
	@Bean
	public Step step1Authenticate(){
		return stepBuilders.get("step1Authenticate")
				.tasklet(authenticate())
				.build();
	}

	@Bean
	public Step step2ETL(){
		return stepBuilders.get("step2ETL")
				.<Product,Product>chunk(5)
				.reader(reader())
 				.writer(writer())
 				.build();
	}

}
