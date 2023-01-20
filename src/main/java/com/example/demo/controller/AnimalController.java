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
import com.example.demo.model.Species;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.SpeciesRepository;

import jakarta.validation.Valid;

/**
 * @author antPinot
 *
 */

@Controller
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private SpeciesRepository speciesRepository;

	@GetMapping("/animal")
	public String listAnimal(Model model) {
		List<Animal> animals = animalRepository.findAll();
		model.addAttribute("animalsList", animals);
		return "list_animal";
	}
	
	@GetMapping("/animal/{id}")
	public String initUpdateAnimal(@PathVariable("id") Integer id ,Model model) {
		Optional<Animal> animal = animalRepository.findById(id);
		if (animal.isPresent()) {
			List<Species> speciesList = speciesRepository.findAll();
			model.addAttribute("speciesList", speciesList);
			model.addAttribute(animal.get());
			return "update_animal";
		}
		return "error";
	}
	
	@GetMapping("animal/create")
	public String createAnimal(Model model) {
		List<Species> speciesList = speciesRepository.findAll();
		model.addAttribute("speciesList", speciesList);
		model.addAttribute("animal",new Animal());
		return "create_animal";
	}
	
	@PostMapping("/animal")
	public String createOrUpdate(@Valid Animal animal, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<Species> speciesList = speciesRepository.findAll();
			model.addAttribute("speciesList", speciesList);
			return "create_animal";
		}
		animalRepository.save(animal);
		return "redirect:/animal";
	}
	
	@GetMapping("/animal/delete/{id}")
	public String deleteAnimal(@PathVariable("id") Integer id) {
		Optional<Animal> animalToDelete = animalRepository.findById(id);
		if (animalToDelete.isPresent()) {
			animalRepository.delete(animalToDelete.get());
			return "redirect:/animal";
		}
		return "error";
	}

}