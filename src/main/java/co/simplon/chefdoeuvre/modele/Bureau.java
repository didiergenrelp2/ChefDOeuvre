package co.simplon.chefdoeuvre.modele;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bureau")
public class Bureau {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_bureau;

	@NotNull
	@Column(unique = true)
	private String nom_bureau;
	@Size(max = 6)
	private Long code_regate;
	private String adresse;
	@Size(max = 5)
	private Long code_postal;
	private String ville;
	@Size(max = 10)
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bureau")
	private Set<Materiel> materiel = new HashSet<>();

	public Bureau() {

	}

	public Bureau(String nom_bureau, Long code_regate, String adresse, Long code_postal, String ville,
			String telephone) {

		this.nom_bureau = nom_bureau;
		this.code_regate = code_regate;
		this.adresse = adresse;
		this.code_postal = code_postal;
		this.ville = ville;
		this.telephone = telephone;
	}

	// Getters et Setters

	public Long getId_bureau() {
		return id_bureau;
	}

	public void setId_bureau(Long id_bureau) {
		this.id_bureau = id_bureau;
	}

	public String getNom_bureau() {
		return nom_bureau;
	}

	public void setNom_bureau(String nom_bureau) {
		this.nom_bureau = nom_bureau;
	}

	public Long getCode_regate() {
		return code_regate;
	}

	public void setCode_regate(Long code_regate) {
		this.code_regate = code_regate;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Long getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(Long code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Set<Materiel> getMateriel() {
		return materiel;
	}

	public void setMateriel(Set<Materiel> materiel) {
		this.materiel = materiel;
	}

}
