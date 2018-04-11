package co.simplon.chefdoeuvre.controleur;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.chefdoeuvre.dao.MaterielDAO;
import co.simplon.chefdoeuvre.modele.Bureau;
import co.simplon.chefdoeuvre.modele.Materiel;
import co.simplon.chefdoeuvre.service.MaterielService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MaterielControleur {

	@Autowired
	private MaterielService materielService;
	@Autowired
	private MaterielDAO materielDAO;

	// exemple en SQL :
	// SELECT * FROM materiel;
	@GetMapping(path = "/materiels")
	public @ResponseBody Iterable<Materiel> recupererToutMateriel() throws Exception {
		return materielService.recupererToutMateriel();
	}

	/**
	 * Rechercher le materiel avec un critère de recherche sur tous les champs
	 *
	 * @param recherche
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/materiels/{recherche}")
	public ResponseEntity<List<Materiel>> recupererMaterielTrie(@PathVariable(value = "recherche") String recherche)
			throws Exception {

		List<Materiel> listeMateriel = materielDAO.recupererMaterielTrie(recherche);
		if (listeMateriel == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(listeMateriel);

	}

	// exemple en SQL :
	// SELECT * FROM materiel WHERE id_materiel=3;
	@GetMapping(path = "/materiel/{id}")
	ResponseEntity<Materiel> recupererMateriel(@PathVariable(value = "id") long id_materiel) throws Exception {
		Materiel materiel = materielService.recupererMateriel(id_materiel);
		if (materiel == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(materiel);
	}

	// exemple en SQL :
	// DELETE FROM materiel WHERE id_materiel=3;
	@DeleteMapping(path = "/materiel/{id}")
	ResponseEntity<Materiel> supprimerMateriel(@PathVariable(value = "id") long id_materiel) throws Exception {
		Materiel materiel = materielService.recupererMateriel(id_materiel);
		if (materiel == null)
			return ResponseEntity.notFound().build();

		materielService.supprimerMateriel(id_materiel);
		return ResponseEntity.ok().build();
	}

	/**
	 * Supprimer un materiel du bureau
	 * 
	 * @param id_materiel_bureau
	 * @param id_materiel_materiel
	 * @return
	 * @throws Exception
	 */

	@DeleteMapping(path = "/bureau/{id_bureau}/suppMateriel/{id_materiel}")
	public ResponseEntity<?> supprimerMaterielDuBureau(@PathVariable(value = "id_bureau") long id_bureau,
			@PathVariable(value = "id_materiel") long id_materiel) throws Exception {
		try {
			materielDAO.supprimerMaterielDuBureau(id_bureau, id_materiel);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	// TODO modifier le comm
	// exemple en SQL :
	// INSERT INTO materiel (`marque`, `modele`, `type`, `calibre`, `numero_serie`,
	// `infos_complementaire`) VALUES ('Berretta', '93', 'Pistolet', '9mm',
	// '1235TYU678', '');
	@PostMapping(path = "/materiels")
	Materiel ajouterMateriel(@Valid @RequestBody Materiel materiel) throws Exception {
		return materielService.ajouterMateriel(materiel);
	}

	/**
	 * Lier le materiel au bureau
	 * 
	 * @param bureauLien
	 * @return
	 * @throws Exception
	 */
	// TODO lier le matos a la table bureau
	@PostMapping(path = "/bureau/lierMateriel")
	ResponseEntity<?> lierMaterielAuBureau(@Valid @RequestBody Bureau bureau, @Valid @RequestBody Materiel materiel)
			throws Exception {
		long id_bureau = bureau.getId_bureau();
		long id_materiel = materiel.getId_materiel();
		try {
			materielDAO.lierMaterielAuBureau(id_bureau, id_materiel);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	// exemple en SQL :
	// UPDATE materiel SET `marque` = "Lexmark", `modele` = "360DN" WHERE
	// id_materiel = 3;
	@PutMapping(path = "/materiel/{id}")
	ResponseEntity<Materiel> miseAJourMateriel(@PathVariable(value = "id") long id_materiel,
			@Valid @RequestBody Materiel materiel) throws Exception {
		Materiel materielAModifier = materielService.recupererMateriel(id_materiel);
		if (materielAModifier == null)
			return ResponseEntity.notFound().build();

		// Mise à jour des attributs obligatoires
		materielAModifier.setId_materiel(materiel.getId_materiel());
		materielAModifier.setDomaine(materiel.getDomaine());
		materielAModifier.setType(materiel.getType());
		materielAModifier.setEtat(materiel.getEtat());

		// Mise à jour des attributs non null

		if (materiel.getMarque() != null)
			materielAModifier.setMarque(materiel.getMarque());

		if (materiel.getModele() != null)
			materielAModifier.setModele(materiel.getModele());

		if (materiel.getNumero_serie() != null)
			materielAModifier.setNumero_serie(materiel.getNumero_serie());

		if (materiel.getCode_parc() != null)
			materielAModifier.setCode_parc(materiel.getCode_parc());

		if (materiel.getCode_article() != null)
			materielAModifier.setCode_article(materiel.getCode_article());

		if (materiel.getDate_fin_garantie() != null)
			materielAModifier.setDate_fin_garantie(materiel.getDate_fin_garantie());

		Materiel materielModifie = materielService.miseAJourMateriel(id_materiel, materielAModifier);
		return ResponseEntity.ok(materielModifie);
	}
}
