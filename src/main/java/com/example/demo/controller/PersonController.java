/**
 * 
 */
package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.PersonRepository;

import jakarta.validation.Valid;

/**
 * @author antPinot
 *
 */

@Controller
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@GetMapping("/person")
	public String listPerson(Model model) {
		List<Person> persons = personRepository.findAll();
		model.addAttribute("personsList", persons);
		return "list_person";
	}

	@GetMapping("/person/{id}")
	public String initUpdatePerson(@PathVariable("id") Integer id, Model model) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			List<Animal> animalsList = animalRepository.findAll();
			model.addAttribute("animalsList", animalsList);
			model.addAttribute("person", person.get());
			return "update_person";
		}
		return "error";
	}

	@GetMapping("/person/create")
	public String createPerson(Model model) {
		List<Animal> animalsList = animalRepository.findAll();
		model.addAttribute("animalsList", animalsList);
		model.addAttribute("person", new Person());
		return "create_person";
	}
	
	@PostMapping("/person")
	public String createOrUpdate(@Valid Person person, BindingResult result, Model model) {
		if(result.hasErrors()){
			List<Animal> animalsList = animalRepository.findAll();
			model.addAttribute("animalsList", animalsList);
			return "create_person";
		}
		personRepository.save(person);
		return "redirect:/person";
	}
	
	@GetMapping("/person/delete/{id}")
	public String deletePerson(@PathVariable("id") Integer id) {
		Optional<Person> personToDelete = personRepository.findById(id);
		if (personToDelete.isPresent()) {
			personRepository.delete(personToDelete.get());
			return "redirect:/person";
		}
		return "error";
	}
}
