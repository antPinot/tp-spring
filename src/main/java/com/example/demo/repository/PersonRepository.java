/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Animal;
import com.example.demo.model.Person;

/**
 * @author antPinot
 *
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> , PersonRepositoryCustom{
	
	List<Person> findByFirstnameOrLastname(String firstname, String lastname);
	
	List<Person> findByAgeGreaterThanEqual(int age);
	
	@Query("from Person where age between :minAge and :maxAge")
	List<Person> findBetweenMinAgeAndMaxAge(@Param("minAge") int minAge, @Param("maxAge")  int maxAge);
	
	@Query("SELECT DISTINCT p FROM Person p JOIN p.animals a WHERE a = :animal")
	List<Person> findAllPersonsByAnimal(@Param("animal") Animal animal);

}
