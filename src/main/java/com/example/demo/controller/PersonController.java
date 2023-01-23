/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

import jakarta.validation.Valid;

/**
 * @author antPinot
 *
 */

@RestController
@RequestMapping({ "/api/person", "/api/person/" })
public class PersonController {

	@Autowired
	private PersonService personService;

	// Create
	@PostMapping
	public Person createPerson(@RequestBody @Valid Person personToCreate) {
		if (personToCreate.getId() != null) {
			throw new BadRequestException("ID is provided in body");
		}
		return personService.create(personToCreate);
	}

	// FindById
	@GetMapping("/{id}")
	public Person findById(@PathVariable("id") Integer id) {
		return personService.findById(id);
	}

	@GetMapping("/persons")
	// FindAll
	public List<Person> findAll() {
		return personService.findAll();
	}

	@GetMapping("/persons/")
	public Page<Person> findWithPagination(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "5") Integer items) {
		return personService.findAllPageable(PageRequest.of((page - 1), items));
	}

	// Update
	@PutMapping("/{id}")
	public Person updatePerson(@PathVariable("id") Integer id, @RequestBody @Valid Person updatePerson) {
		if (!id.equals(updatePerson.getId())) {
			throw new BadRequestException("ID is not provided in body or different in body and url");
		}
		return personService.update(updatePerson);
	}

	// Delete
	@DeleteMapping("/{id}")
	public void deletePerson(@PathVariable("id") Integer id) {
		personService.delete(id);
	}

}
