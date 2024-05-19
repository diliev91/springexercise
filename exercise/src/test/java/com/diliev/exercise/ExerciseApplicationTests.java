package com.diliev.exercise;

import com.diliev.exercise.example.entity.Person;
import com.diliev.exercise.example.service.ExampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ExerciseApplicationTests {

	@Autowired
	ExampleService service;
	@Test
	void testService() {
		var person = new Person(null, "test@abv.bg", "mitko", "0899447251");
		var result = service.addPerson(person);

		assert person.getNumber().equals(result.getNumber());
		assert person.getName().equals(result.getName());
		assert person.getEmail().equals(result.getEmail());

	}
}
