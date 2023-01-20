/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Person;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * @author antPinot
 *
 */
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void supprimerPersonnesSansAnimaux() {

		TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
		List<Person> persons = query.getResultList();

		persons.forEach(p -> {

			if (p.getAnimals().isEmpty()) {
				System.out.println(p.getFirstname() + " " + p.getLastname() + " est supprimé de la base");
				em.remove(p);
			}

		});

	}

	@Override
	@Transactional
	public void ajouterPersonnes(Integer nbPersonnes) {
		
		Faker faker = new Faker();
		
		for (int i=0; i<nbPersonnes; i++) {
			Person person = new Person();
			person.setFirstname(faker.name().firstName());
			person.setLastname(faker.name().lastName());
			em.persist(person);
		}

	}

}
