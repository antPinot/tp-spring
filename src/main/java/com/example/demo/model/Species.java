/**
 * 
 */
package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author antPinot
 *
 */

@Entity
@Table(name = "species")
public class Species {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "common_name", columnDefinition = "varchar(50)", nullable = true)
	private String commonName;

	@Column(name = "latin_name", columnDefinition = "varchar(200)", nullable = true)
	private String latinName;

	@OneToMany(mappedBy = "specie")
	private List<Animal> animals = new ArrayList<>();

	/**
	 * Getter pour l'attribut id
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter pour l'attribut id
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter pour l'attribut commonName
	 * 
	 * @return the commonName
	 */
	public String getCommonName() {
		return commonName;
	}

	/**
	 * Setter pour l'attribut commonName
	 * 
	 * @param commonName the commonName to set
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	/**
	 * Getter pour l'attribut latinName
	 * 
	 * @return the latinName
	 */
	public String getLatinName() {
		return latinName;
	}

	/**
	 * Setter pour l'attribut latinName
	 * 
	 * @param latinName the latinName to set
	 */
	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	/**
	 * Getter pour l'attribut animals
	 * 
	 * @return the animals
	 */
	public List<Animal> getAnimals() {
		return animals;
	}

	/**
	 * Setter pour l'attribut animals
	 * 
	 * @param animals the animals to set
	 */
	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	@Override
	public String toString() {
		return "Species [id=" + id + ", commonName=" + commonName + ", latinName=" + latinName + ", animals=" + animals
				+ "]";
	}
	

}
