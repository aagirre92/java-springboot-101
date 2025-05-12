package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
@RestController
public class DemoApplication {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Path dataFilePath;

	// Constructor (tipo def __init__ python)
	public DemoApplication() {
		try {
			// Use a path that works in both development and production
			// In development, this will be in your project root
			dataFilePath = Paths.get("src/main/resources/people.json").toAbsolutePath();

			// Ensure the file exists
			if (!Files.exists(dataFilePath)) {
				// Create parent directories if needed
				Files.createDirectories(dataFilePath.getParent());
				// Create an empty array if file doesn't exist
				objectMapper.writeValue(dataFilePath.toFile(), new ArrayList<>());
			}

			System.out.println("Using data file at: " + dataFilePath);
		} catch (IOException e) {
			throw new RuntimeException("Failed to initialize data file", e);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private List<Person> readPeople() {
		try {
			return objectMapper.readValue(dataFilePath.toFile(), new TypeReference<List<Person>>() {});
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private void writePeople(List<Person> people) {
		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(dataFilePath.toFile(), people);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Rest of your controller methods remain the same
	@GetMapping("/people")
	public List<Person> getAllPeople() {
		return readPeople();
	}

	@PostMapping("/people")
	public ResponseEntity<String> addPerson(@RequestBody Person newPerson) {
		List<Person> people = readPeople();

		if (people.stream().anyMatch(p -> p.getId() == newPerson.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Person with this ID already exists.");
		}

		people.add(newPerson);
		writePeople(people);
		return ResponseEntity.ok("Person added.");
	}

	@DeleteMapping("/people/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable int id) {
		List<Person> people = readPeople();
		boolean removed = people.removeIf(p -> p.getId() == id);

		if (removed) {
			writePeople(people);
			return ResponseEntity.ok("Person deleted.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
		}
	}

	@GetMapping("/people/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable int id) {
		List<Person> people = readPeople();
		return people.stream()
				.filter(p -> p.getId() == id)
				.findFirst()
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}