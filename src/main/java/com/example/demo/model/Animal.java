/**
 * 
 */
package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.enums.Sex;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author antPinot
 *
 */

@Entity
@Table(name = "animal")
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "varchar(50)", nullable = true)
	private String color;
	
	@Column(columnDefinition = "varchar(50)", nullable = true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@ManyToOne
	@JoinColumn(name = "species_id")
	private Species specie;
	
	@ManyToMany(mappedBy = "animals")
	//@JoinTable(name = "person_animals", joinColumns = @JoinColumn(name = "animals_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
	private List<Person> persons = new ArrayList<>();

	/**Getter pour l'attribut id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**Setter pour l'attribut id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**Getter pour l'attribut color
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**Setter pour l'attribut color
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**Getter pour l'attribut name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**Setter pour l'attribut name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**Getter pour l'attribut sex
	 * @return the sex
	 */
	public Sex getSex() {
		return sex;
	}

	/**Setter pour l'attribut sex
	 * @param sex the sex to set
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	/**Getter pour l'attribut persons
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return persons;
	}

	/**Setter pour l'attribut persons
	 * @param persons the persons to set
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	/*@Override
	public String toString() {
		return "Animal [id=" + id + ", color=" + color + ", name=" + name + ", sex=" + sex + ", specie=" + specie
				+ ", persons=" + persons + "]";
	}
	
	*/

}
