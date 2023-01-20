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
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Species;
import com.example.demo.service.SpeciesService;

/**
 * @author antPinot
 *
 */

@Controller
@RequestMapping("/api/specie")
public class SpeciesController {
	
	@Autowired
	private SpeciesService speciesService;
	
	@PostMapping
	public Species createSpecie (Species specieToCreate) {
		return speciesService.createSpecie(null);
	}
	
	@GetMapping("/{id}")
	public Species findById(@PathVariable("id") Integer id) {
		return speciesService.findById(id);
	}
	
	@GetMapping("/species")
	public List<Species> findAll(){
		return speciesService.findAll();
	}
	
	@PutMapping
	public Species updateSpecie(Species updatedSpecie) {
		return speciesService.updateSpecie(updatedSpecie);
	}
	
	@DeleteMapping
	public void deleteSpecie(Species specieToDelete) {
		speciesService.deleteSpecie(specieToDelete);
	}

}
