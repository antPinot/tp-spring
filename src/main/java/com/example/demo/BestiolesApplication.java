package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.enums.Sex;
import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.example.demo.model.Species;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.SpeciesRepository;

@SpringBootApplication
public class BestiolesApplication implements CommandLineRunner {

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private SpeciesRepository speciesRepository;

	public static void main(String[] args) {
		SpringApplication.run(BestiolesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Afficher la liste des entités avec findAll
		Iterable<Animal> animals = this.animalRepository.findAll();
		Iterable<Person> persons = this.personRepository.findAll();
		Iterable<Species> species = this.speciesRepository.findAll();

		List<Animal> animalsList = new ArrayList<>();

		for (Animal animal : animals) {
			animalsList.add(animal);
		}

		List<Species> speciesList = new ArrayList<>();

		for (Species specie : species) {
			speciesList.add(specie);
		}

		for (Animal animal : animals) {
			System.out.println("Animal : " + animal.getName());
		}

		// Créer une entité avec la méthode Save
		Person personToAdd = new Person();
		personToAdd.setFirstname("Jean-Clair");
		personToAdd.setLastname("Todibo");
		personToAdd.setAge(23);
		personToAdd.setAnimals(animalsList);

		personRepository.save(personToAdd);

		Animal animalWithNoOwner = new Animal();
		animalWithNoOwner.setName("Dibu");
		
		animalRepository.save(animalWithNoOwner);
		
		animalsList.add(animalWithNoOwner);

		for (Person person : persons) {
			System.out.println("Person : " + person.getFirstname());
		}

		// Rechercher une entité par son id avec findById
		System.out.println("Jean-Clair est là : " + personRepository.findById(9).stream()
				.map(p -> p.getFirstname() + p.getLastname() + p.getAge()).toString());
		System.out.println("Il y a " + StreamSupport.stream(personRepository.findAll().spliterator(), false).count()
				+ " personnes en base");

		for (Species specie : species) {
			System.out.println("Latin Name : " + specie.getLatinName());
		}

		// personRepository.deleteById(12);

		System.out.println("Il y a " + StreamSupport.stream(personRepository.findAll().spliterator(), false).count()
				+ " personnes en base");

		/*
		 * System.out.println("La taille de la liste de résultats est de " +
		 * StreamSupport.stream(speciesRepository.findFirstByCommonName("Chien").
		 * spliterator(), false).count() + " résultats");
		 * System.out.println(speciesRepository.findFirstByCommonName("Chien").get(0).
		 * getCommonName());
		 */

		System.out.println(speciesRepository.findByLatinNameContainsAllIgnoreCase("LuPuS").get(0).getCommonName());

		System.out.println(personRepository.findByFirstnameOrLastname("Jean-Clair", null).get(0).getFirstname());

		List<Person> resultatAgePlusGrand = personRepository.findByAgeGreaterThanEqual(44);

		System.out.println("Il y a " + resultatAgePlusGrand.size() + " personnes de plus de 44 ans");

		for (Person person : resultatAgePlusGrand) {
			System.out.println(person.getFirstname() + " " + person.getLastname());
		}

		List<Animal> resultatsAnimaux = animalRepository.findBySpecie(speciesList.get(0));

		resultatsAnimaux.forEach(a -> System.out.println(a.getName()));

		List<String> colors = new ArrayList<>(Arrays.asList("Brun", "Roux", "Arc-en-ciel"));

		List<Animal> resultatsAnimauxCouleur = animalRepository.findByColorIn(colors);

		resultatsAnimauxCouleur.forEach(a -> System.out.println(a.getName()));

		speciesRepository.findAllOrderedByCommonNameAscSql().forEach(s -> System.out.println(s.getCommonName()));

		speciesRepository.findByCommonNameLikeJpql("hie").forEach(s -> System.out.println(s.getCommonName()));

		personRepository.findBetweenMinAgeAndMaxAge(25, 70)
				.forEach(p -> System.out.println(p.getFirstname() + " " + p.getLastname()));

		System.out.println("Nombre de femelles : " + animalRepository.countAnimalsBySex(Sex.F));

		personRepository.findAllPersonsByAnimal(animalsList.get(0))
				.forEach(p -> System.out.println(p.getFirstname() + " " + p.getLastname()));
		
		Person noAnimalPerson = new Person();
		noAnimalPerson.setFirstname("Dibu");
		noAnimalPerson.setLastname("Martinez le gamin");
		personRepository.save(noAnimalPerson);

		personRepository.supprimerPersonnesSansAnimaux();

		for (Animal animal : animalsList) {
			if (animalRepository.hasAPerson(animal)) {
				System.out.println(animal.getName() + " a un propriétaire");
			} else {
				System.out.println(animal.getName() + " n'a pas de propriétaire");
			}
		}

		personRepository.ajouterPersonnes(2);

	}

}
