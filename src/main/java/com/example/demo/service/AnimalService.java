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

import com.example.demo.enums.Sex;
import com.example.demo.exceptions.AnimalNotFoundException;
import com.example.demo.model.Animal;
import com.example.demo.model.Species;
import com.example.demo.repository.AnimalRepository;

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
	public Animal createAnimal(@Valid Animal animalToCreate) throws NullPointerException{
		if (animalToCreate == null) {
			throw new NullPointerException();
		}
		return animalRepository.save(animalToCreate);
	}

	// Read
	public Animal findById(Integer id) throws AnimalNotFoundException{
		return animalRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException(id));
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
	public void deleteAnimal(Integer id) throws AnimalNotFoundException {
		Optional<Animal> animalToDelete = animalRepository.findById(id);
		if (animalToDelete.isPresent()) {
			animalRepository.delete(animalToDelete.get());
		}else {
			throw new AnimalNotFoundException(id);
		}
	}
	
	//Pagination
	
	public Page<Animal> findAllPageable(Pageable pageable){
		return animalRepository.findAll(pageable);
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
