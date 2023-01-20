/**
 * 
 */
package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Species;
import com.example.demo.repository.SpeciesRepository;

import jakarta.validation.Valid;

/**
 * @author antPinot
 *
 */

@Controller
public class SpeciesController {

	@Autowired
	private SpeciesRepository speciesRepository;

	@GetMapping("/species")
	public String listSpecies(Model model) {
		List<Species> species = speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName"));
		model.addAttribute("speciesList", species);
		return "list_specie";
	}

	@GetMapping("/species/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Species> species = speciesRepository.findById(id);
		if (species.isPresent()) {
			model.addAttribute("species", species.get());
			List<Species> speciesList = speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName"));
			model.addAttribute("speciesList", speciesList);
			return "update_species";
		}
		return "error";
	}

	@GetMapping("/species/create")
	public String initCreate(Model model) {
		model.addAttribute("species", new Species());
		return "create_species";
	}

	@PostMapping("/species")
	public String createOrUpdate(@Valid Species species, BindingResult result) {
		if (result.hasErrors()) {
			return "create_species";
		}
		speciesRepository.save(species);
		return "redirect:/species";
	}

	@GetMapping("/species/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		Optional<Species> specieToDelete = speciesRepository.findById(id);
		if (specieToDelete.isPresent()) {
			speciesRepository.delete(specieToDelete.get());
			return "redirect:/species";
		}
		return "error";
	}

}
