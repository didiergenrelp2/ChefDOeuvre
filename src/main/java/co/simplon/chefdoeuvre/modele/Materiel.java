package co.simplon.chefdoeuvre.modele;

import java.util.Date;

//import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import co.simplon.chefdoeuvre.util.JSONMaterielDeserializer;
import co.simplon.chefdoeuvre.util.JSONMaterielSerializer;

@Entity
//@Audited //Audite les modifications sur la table
@Table(name = "materiel")
@JsonDeserialize(using = JSONMaterielDeserializer.class)
@JsonSerialize(using = JSONMaterielSerializer.class)
public class Materiel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_materiel;
	@Enumerated
	private Domaine domaine;
	@NotNull
	private String type;
	private String marque;
	private String modele;
	private String numero_serie;
	private String code_parc;
	private String code_article;
	private Date date_fin_garantie;
	private Etat etat;
	// private Date dateMiseAJour;
	// private int quantite;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bureau", nullable = true)
	@JsonBackReference
	private Bureau bureau;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "id_utilisateur", nullable = false)
	// private Utilisateur utilisateur;

	public Materiel() {

	}

	public Materiel(Domaine domaine, String type, String marque, String modele, String numero_serie, String code_parc,
			String code_article, Date date_fin_garantie, Etat etat) {

		this.domaine = domaine;
		this.type = type;
		this.marque = marque;
		this.modele = modele;
		this.numero_serie = numero_serie;
		this.code_parc = code_parc;
		this.code_article = code_article;
		this.date_fin_garantie = date_fin_garantie;
		this.etat = etat;

	}

	// Getters et Setters

	public Long getId_materiel() {
		return id_materiel;
	}

	public void setId_materiel(Long id_materiel) {
		this.id_materiel = id_materiel;
	}
	
	@Enumerated(EnumType.STRING)
	public Domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getNumero_serie() {
		return numero_serie;
	}

	public void setNumero_serie(String numero_serie) {
		this.numero_serie = numero_serie;
	}

	public String getCode_parc() {
		return code_parc;
	}

	public void setCode_parc(String code_parc) {
		this.code_parc = code_parc;
	}

	public String getCode_article() {
		return code_article;
	}

	public void setCode_article(String code_article) {
		this.code_article = code_article;
	}

	public Date getDate_fin_garantie() {
		return date_fin_garantie;
	}

	public void setDate_fin_garantie(Date date_fin_garantie) {
		this.date_fin_garantie = date_fin_garantie;
	}
	
	@Enumerated(EnumType.STRING)
	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public Bureau getBureau() {
		return bureau;
	}

	public void setBureau(Bureau bureau) {
		this.bureau = bureau;
	}

}
