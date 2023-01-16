/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

/**
 * @author antPinot
 *
 */

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
	
	List<Person> findByFirstnameOrLastname(String firstname, String lastname);
	
	List<Person> findByAgeGreaterThanEqual(int age);
	
	@Query(value = "from Person where age between :minAge and :maxAge")
	List<Person> findBetweenMinAgeAndMaxAge(@Param("minAge") int minAge, @Param("maxAge")  int maxAge);

}
