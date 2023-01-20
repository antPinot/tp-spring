/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
	
	public List<Animal> findBySpecie(Species specie);
	
	public List<Animal> findByColorIn(List<String> colors);
	
	@Query("SELECT COUNT(a) FROM Animal a where a.sex = :sex ")
	public Integer countAnimalsBySex(@Param("sex")Sex sex);
	
	/*@Query(value = "CASE WHEN EXISTS (SELECT COUNT(p) FROM Person p JOIN p.animals a WHERE a = :animal) THEN 1 ELSE 0 END", nativeQuery = true)
	boolean hasAPerson(@Param("animal") Animal animal);*/

}
