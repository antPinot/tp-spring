/**
 * 
 */
package com.example.demo.service;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 * @author antPinot
 *
 */

@Component
public class BavardService {
	
	private String nom = "Je suis trop bavard";

	/**Getter pour l'attribut nom
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**Setter pour l'attribut nom
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String parler() {
		return this.nom + " from : " + getClass().getSimpleName();
	}
	
	@PostConstruct
	private void postConstruct() {
		System.out.println("Je suis VRAIMENT trop bavard");
	}

}
