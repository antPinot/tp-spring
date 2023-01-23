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
import com.example.demo.model.Animal;
import com.example.demo.service.AnimalService;

import jakarta.validation.Valid;

/**
 * @author antPinot
 *
 */

@RestController
@RequestMapping({ "/api/animal", "/api/animal/" })
public class AnimalController {

	@Autowired
	private AnimalService animalService;

	@PostMapping
	public Animal createAnimal(@RequestBody @Valid Animal animalToCreate) {
		if (animalToCreate.getId() != null) {
			throw new BadRequestException("ID is provided in body");
		}
		return animalService.createAnimal(animalToCreate);
	}

	@GetMapping("/{id}")
	public Animal findById(@PathVariable("id") Integer id) {
		return animalService.findById(id);
	}

	@GetMapping("/animals")
	public List<Animal> findAll() {
		return animalService.findAll();
	}
	
	@GetMapping("/animals/")
	public Page<Animal> findWithPagination(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "5") Integer items) {
		return animalService.findAllPageable(PageRequest.of((page - 1), items));
	}

	@PutMapping("/{id}")
	public Animal updateAnimal(@PathVariable("id") Integer id, @RequestBody @Valid Animal updatedAnimal) {
		if (!id.equals(updatedAnimal.getId())) {
			throw new BadRequestException("ID is not provided in body or different in body and url");
		}
		return animalService.updateAnimal(updatedAnimal);
	}

	@DeleteMapping("/{id}")
	public void deleteAnimal(@PathVariable("id") Integer id) {
		animalService.deleteAnimal(id);
	}

}
