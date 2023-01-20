/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Species;
import com.example.demo.repository.SpeciesRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

/**
 * @author antPinot
 *
 */

@Service
public class SpeciesService {

	@Autowired
	private SpeciesRepository speciesRepository;

	// Create
	public Species createSpecie(@Valid Species specieToCreate) {
		return speciesRepository.save(specieToCreate);
	}

	// Read
	public Species findById(Integer id) {
		return speciesRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
	
	// Read All
	public List<Species> findAll() {
		return speciesRepository.findAll();
	}

	// Update
	public Species updateSpecie(@Valid Species specieToUpdate) {
		return speciesRepository.save(specieToUpdate);
	}
	
	//Delete
	public void deleteSpecie(@Valid Species specieToDelete) {
		speciesRepository.delete(specieToDelete);
	}

	// Méthodes "passe-plat"
	public List<Species> findFirstByCommonName(String commonName) {
		return speciesRepository.findByCommonNameLikeJpql(commonName);
	}

	public List<Species> findByLatinNameContainsAllIgnoreCase(String query) {
		return speciesRepository.findByLatinNameContainsAllIgnoreCase(query);
	}

	public List<Species> findAllOrderedByCommonNameAscSql() {
		return speciesRepository.findAllOrderedByCommonNameAscSql();
	}

	public List<Species> findByCommonNameLikeJpql(String commonName) {
		return speciesRepository.findByCommonNameLikeJpql(commonName);
	}

}
