/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.enums.Sex;
import com.example.demo.model.Animal;
import com.example.demo.model.Species;

/**
 * @author antPinot
 *
 */

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
	
	List<Animal> findBySpecie(Species specie);
	
	List<Animal> findByColorIn(List<String> colors);
	
	@Query("SELECT COUNT(a) FROM Animal a where a.sex = :sex ")
	Integer countAnimalsBySex(@Param("sex")Sex sex);
	
	@Query("SELECT CASE WHEN (COUNT(a)>0) THEN true ELSE false END FROM Person p JOIN p.animals a WHERE a = :animal")
	boolean hasAPerson(@Param("animal") Animal animal);

}
