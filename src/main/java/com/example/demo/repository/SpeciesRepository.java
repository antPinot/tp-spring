/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Species;

/**
 * @author antPinot
 *
 */

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

	List<Species> findFirstByCommonName(String commonName);

	List<Species> findByLatinNameContainsAllIgnoreCase(String query);

	// Méthode qui va aller chercher toutes les Species, ordonnées par nom commun
	// ascendant.
	@Query(value = "SELECT * FROM species ORDER BY common_name ASC", nativeQuery = true)
	List<Species> findAllOrderedByCommonNameAscSql();

	@Query("from Species where commonName like %:commonName%")
	List<Species> findByCommonNameLikeJpql(@Param("commonName") String commonName);

}
