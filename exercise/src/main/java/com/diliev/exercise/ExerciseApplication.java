package com.diliev.exercise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext =
		SpringApplication.run(ExerciseApplication.class, args);

		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		for(String beanName : allBeanNames) {
			log.info(beanName);
		}
		log.info("Spring application started.");
	}
}

