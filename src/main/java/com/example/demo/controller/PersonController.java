/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

/**
 * @author antPinot
 *
 */

@RestController
@RequestMapping({"/api/person", "/api/person/"})
public class PersonController {

	@Autowired
	private PersonService personService;

	// Create
	@PostMapping
	public Person createPerson(@RequestBody @Valid Person personToCreate) {
		return personService.create(personToCreate);
	}

	// FindById
	@GetMapping("/{id}")
	public Person findById(@PathVariable("id") Integer id){
		try {
			return personService.findById(id);
		} catch (EntityNotFoundException e) {
			System.out.println("Entity Not Found");
			return null;
		}
	}

	@GetMapping("/persons")
	// FindAll
	public List<Person> findAll() {
		return personService.findAll();
	}

	// Update
	@PutMapping
	public Person updatePerson(@RequestBody @Valid Person updatePerson) {
		return personService.update(updatePerson);
	}

	// Delete
	@DeleteMapping("/{id}")
	public void deletePerson(@PathVariable("id") Integer id) {
		personService.delete(id);
	}

}
