/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.Sex;
import com.example.demo.model.Animal;
import com.example.demo.model.Species;
import com.example.demo.repository.AnimalRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

/**
 * @author antPinot
 *
 */

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRepository;

	// Create
	public Animal createAnimal(@Valid Animal animalToCreate) {
		return animalRepository.save(animalToCreate);
	}

	// Read
	public Animal findById(Integer id) {
		return animalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	// Read All
	public List<Animal> findAll() {
		return animalRepository.findAll();
	}

	// Update
	public Animal updateAnimal(@Valid Animal animalToUpdate) {
		return animalRepository.save(animalToUpdate);
	}

	// Delete
	public void deleteAnimal(@Valid Animal animalToDelete) {
		animalRepository.delete(animalToDelete);
	}

	// Méthodes "passe-plat"

	public List<Animal> findBySpecie(@Valid Species specie) {
		return animalRepository.findBySpecie(specie);
	}

	public List<Animal> findByColorIn(List<String> colors) {
		return animalRepository.findByColorIn(colors);
	}

	public Integer countAnimalsBySex(Sex sex) {
		return animalRepository.countAnimalsBySex(sex);
	}

}
