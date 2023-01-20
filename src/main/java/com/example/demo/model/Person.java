/**
 * 
 */
package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * @author antPinot
 *
 */

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "int(11)", nullable = true)
	private Integer age;

	@Column(columnDefinition = "varchar(50)", nullable = true)
	private String firstname;

	@Column(columnDefinition = "varchar(50)", nullable = true)
	private String lastname;

	@ManyToMany
	@JoinTable(name = "person_animals", joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "animals_id", referencedColumnName = "id"))
	@JsonIgnoreProperties(value = {"persons"})
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
	 * Getter pour l'attribut age
	 * 
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Setter pour l'attribut age
	 * 
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Getter pour l'attribut firstname
	 * 
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Setter pour l'attribut firstname
	 * 
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Getter pour l'attribut lastname
	 * 
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Setter pour l'attribut lastname
	 * 
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
		return "Person [id=" + id + ", age=" + age + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", animals=" + animals + "]";
	}
	

}
