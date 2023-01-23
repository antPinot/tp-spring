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
import com.example.demo.model.Species;
import com.example.demo.service.SpeciesService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

/**
 * @author antPinot
 *
 */

@RestController
@RequestMapping({ "/api/specie", "api/specie/"})
public class SpeciesController {

	@Autowired
	private SpeciesService speciesService;

	@PostMapping
	public Species createSpecie(@RequestBody @Valid Species specieToCreate) {
		if(specieToCreate.getId() != null) {
			throw new BadRequestException("ID is provided in body");
		}
		return speciesService.createSpecie(specieToCreate);
	}

	@GetMapping("/{id}")
	public Species findById(@PathVariable("id") Integer id) {
		try {
			return speciesService.findById(id);
		} catch (EntityNotFoundException e) {
			System.out.println("Entity Not Found");
			return null;
		}
	}

	@GetMapping("/species")
	public List<Species> findAll() {
		return speciesService.findAll();
	}
	
	@GetMapping("/species/")
	public Page<Species> findWithPagination(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "5") Integer items) {
		return speciesService.findAllPageable(PageRequest.of((page - 1), items));
	}

	@PutMapping("/{id}")
	public Species updateSpecie(@PathVariable("id") Integer id, @RequestBody @Valid Species updatedSpecie) {
		if (!id.equals(updatedSpecie.getId())) {
			throw new BadRequestException("ID is not provided in body or different in body and url");
		}
		return speciesService.updateSpecie(updatedSpecie);
	}

	@DeleteMapping("/{id}")
	public void deleteSpecie(@PathVariable("id") Integer id) {
		speciesService.deleteSpecie(id);
	}

}
