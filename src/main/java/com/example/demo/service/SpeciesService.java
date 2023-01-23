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

import com.example.demo.exceptions.SpeciesNotFoundException;
import com.example.demo.model.Species;
import com.example.demo.repository.SpeciesRepository;


/**
 * @author antPinot
 *
 */

@Service
public class SpeciesService {

	@Autowired
	private SpeciesRepository speciesRepository;

	// Create
	public Species createSpecie(Species specieToCreate) throws NullPointerException{
		if (specieToCreate == null) {
			throw new NullPointerException();
		}
		return speciesRepository.save(specieToCreate);
	}

	// Read
	public Species findById(Integer id) throws SpeciesNotFoundException{
		return speciesRepository.findById(id).orElseThrow(() -> new SpeciesNotFoundException(id));
	}
	
	// Read All
	public List<Species> findAll() {
		return speciesRepository.findAll();
	}

	// Update
	public Species updateSpecie(Species specieToUpdate) {
		return speciesRepository.save(specieToUpdate);
	}
	
	//Delete
	public void deleteSpecie(Integer id) throws SpeciesNotFoundException{
		Optional<Species> specieToDelete = speciesRepository.findById(id);
		if (specieToDelete.isPresent()) {
			speciesRepository.delete(specieToDelete.get());
		}else {
			throw new SpeciesNotFoundException(id);
		}
	}
	
	//Pagination
	
	public Page<Species> findAllPageable(Pageable pageable){
		return speciesRepository.findAll(pageable);
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
