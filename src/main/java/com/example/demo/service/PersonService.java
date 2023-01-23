/**
 * 
 */
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.PersonNotFoundException;
import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

/**
 * @author antPinot
 *
 */

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	//Create
	public Person create(Person personToCreate) throws NullPointerException{
		if(personToCreate == null) {
			throw new NullPointerException();
		}
		return personRepository.save(personToCreate);
	}
	
	//Read
	public Person findById(Integer id) throws PersonNotFoundException {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}
	
	//ReadAll
	public List<Person> findAll() {
		return personRepository.findAll();
	}
	
	//Update
	public Person update(Person personToUpdate) {
		return personRepository.save(personToUpdate);
	}
	
	//Delete
	public void delete(Integer id) throws PersonNotFoundException {
		Optional<Person> personToDelete = personRepository.findById(id);
		if (personToDelete.isPresent()) {
			personRepository.delete(personToDelete.get());
		}else {
			throw new PersonNotFoundException(id);
		}
	}
	
	//Pagination
	public Page<Person> findAllPageable(Pageable pageable){
		return personRepository.findAll(pageable);
	}
	
	// Méthodes "passe-plat"
	
	public List<Person> findByFirstnameOrLastname(String firstname, String lastname){
		return personRepository.findByFirstnameOrLastname(firstname, lastname);
	}
	
	public List<Person> findByAgeGreaterThanEqual(int age){
		return personRepository.findByAgeGreaterThanEqual(age);
	}
	
	public List<Person> findBetweenMinAgeAndMaxAge(int minAge, int maxAge){
		return personRepository.findBetweenMinAgeAndMaxAge(minAge, maxAge);
	}
	
	public List<Person> findAllPersonsByAnimal(Animal animal){
		return personRepository.findAllPersonsByAnimal(animal);
	}
	
	

}
