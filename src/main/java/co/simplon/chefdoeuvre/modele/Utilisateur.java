package co.simplon.chefdoeuvre.modele;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_utilisateur;

	@NotNull
	private String nom;
	private String prenom;
	private String idrh;
	private Fonction fonction;
	private String mdp;
	
	public Utilisateur() {

	}

	public Utilisateur(String nom, String prenom, String idrh, Fonction fonction) {
		this.nom = nom;
		this.prenom = prenom;
		this.idrh = idrh;
		this.fonction = fonction;

	}

	// Getters et Setters

	public Long getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(Long id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getIdrh() {
		return idrh;
	}

	public void setIdrh(String idrh) {
		this.idrh = idrh;
	}

	@Enumerated(EnumType.STRING)
	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
