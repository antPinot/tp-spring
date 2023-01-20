/**
 * 
 */
package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * @author antPinot
 *
 */

@SpringBootTest
@Transactional
class PersonRepositoryTest {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PersonRepository personRepository;
	
	@BeforeEach
	public void initData() {
		Faker faker = new Faker();
		em.clear();
		Animal a1 = new Animal();
		a1.setName(faker.name().firstName());
		em.persist(a1);
		Person p1 = new Person();
		p1.setFirstname(faker.name().firstName());
		p1.setLastname(faker.name().lastName());
		p1.setAge(25);
		p1.setAnimals(Arrays.asList(a1));
		em.persist(p1);
		Person p2 = new Person();
		p2.setFirstname(faker.name().firstName());
		p2.setLastname(faker.name().lastName());
		p2.setAge(50);
		em.persist(p2);
		em.flush();
	}

	@Test
	public void findByAgeGreaterThanEqualTest() {
		List<Person> firstTest = personRepository.findByAgeGreaterThanEqual(20);
		List<Person> secondTest = personRepository.findByAgeGreaterThanEqual(30);
		List<Person> thirdTest = personRepository.findByAgeGreaterThanEqual(60);
		assertEquals(2, firstTest.size());
		assertEquals(1, secondTest.size());
		assertEquals(0, thirdTest.size());
	}
	
	@Test
	public void findPersonWhereAgeBetweenTest() {
		List<Person> firstTest = personRepository.findBetweenMinAgeAndMaxAge(1, 100);
		List<Person> secondTest = personRepository.findBetweenMinAgeAndMaxAge(20, 40);
		List<Person> thirdTest = personRepository.findBetweenMinAgeAndMaxAge(60, 100);
		assertEquals(2, firstTest.size());
		assertEquals(1, secondTest.size());
		assertEquals(0, thirdTest.size());
	}
	
	@Test
	public void ajouterPersonnesTest() {
		assertEquals(2, StreamSupport.stream(personRepository.findAll().spliterator(), false).count());
		personRepository.ajouterPersonnes(2);
		assertEquals(4, StreamSupport.stream(personRepository.findAll().spliterator(), false).count());
	}
	
	@Test
	public void supprimerPersonnesSansAnimauxTest() {
		assertEquals(2, StreamSupport.stream(personRepository.findAll().spliterator(), false).count());
		personRepository.supprimerPersonnesSansAnimaux();
		assertEquals(1, StreamSupport.stream(personRepository.findAll().spliterator(), false).count());
	}
	
	@Test
	public void findAllPersonsByAnimalTest() {
		Animal animalTest = em.find(Animal.class, 1);
		assertEquals(1, personRepository.findAllPersonsByAnimal(animalTest).size());
	}

}
