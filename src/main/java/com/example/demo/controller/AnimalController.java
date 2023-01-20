/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Animal;
import com.example.demo.service.AnimalService;

import jakarta.validation.Valid;

/**
 * @author antPinot
 *
 */

@Controller
@RequestMapping("/api/animal")
public class AnimalController {
	
	@Autowired
	private AnimalService animalService;
	
	@PostMapping
	public Animal createAnimal(@RequestBody @Valid Animal animalToCreate) {
		return animalService.createAnimal(animalToCreate);
	}
	
	@GetMapping("/{id}")
	public Animal findById(@PathVariable("id") Integer id) {
		return animalService.findById(id);
	}
	
	@GetMapping("/animals")
	public List<Animal> findAll(){
		return animalService.findAll();
	}
	
	@PutMapping
	public Animal updateAnimal(@RequestBody @Valid Animal updatedAnimal) {
		return animalService.updateAnimal(updatedAnimal);
	}
	
	@DeleteMapping
	public void deleteAnimal(@RequestBody @Valid Animal animalToDelete) {
		animalService.deleteAnimal(animalToDelete);
	}

}
