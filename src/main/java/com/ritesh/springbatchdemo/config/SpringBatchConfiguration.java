package com.ritesh.springbatchdemo.config;

import com.ritesh.springbatchdemo.model.PersonalDetails;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class SpringBatchConfiguration {

    @Bean("user_personal")
    public Job job(JobBuilderFactory factory, StepBuilderFactory stepBuilderFactory, ItemReader<PersonalDetails> personalDetailsItemReader, ItemProcessor<PersonalDetails, PersonalDetails> personalDetailsItemProcessor, ItemWriter<PersonalDetails> personalDetailsItemWriter) {
        Step step = stepBuilderFactory.get("user_personal_step")
                .<PersonalDetails, PersonalDetails>chunk(100)
                .reader(personalDetailsItemReader)
                .processor(personalDetailsItemProcessor)
                .writer(personalDetailsItemWriter)
                .build();
        return factory.get("user_personal")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<PersonalDetails> personalDetailsItemReader(@Value("${users_personal}") Resource resource) {
        FlatFileItemReader<PersonalDetails> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<PersonalDetails> lineMapper() {

        var delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames(new String[]{"id", "name", "email", "mobile"});

        var fieldSetMapper = new BeanWrapperFieldSetMapper<PersonalDetails>();
        fieldSetMapper.setTargetType(PersonalDetails.class);

        var defaultLineMapper = new DefaultLineMapper<PersonalDetails>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
}
