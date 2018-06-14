package co.simplon.chefdoeuvre.controleur;

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

import co.simplon.chefdoeuvre.modele.Utilisateur;
import co.simplon.chefdoeuvre.service.UtilisateurService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UtilisateurControleur {

	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping(path = "/utilisateurs")
	public @ResponseBody Iterable<Utilisateur> recupererTousLesUtilisateurs() throws Exception {
		return utilisateurService.recupererTousLesUtilisateurs();
	}

	@GetMapping(path = "/utilisateur/{id}")
	public ResponseEntity<Utilisateur> recupererUtilisateur(@PathVariable(value = "id") Long id_utilisateur) throws Exception {
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(id_utilisateur);
		if (utilisateur == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(utilisateur);
		}

	}
	
	@PostMapping(path = "/utilisateurs")
	public ResponseEntity<Utilisateur> ajouterUtilisateur(@Valid @RequestBody Utilisateur utilisateur) throws Exception {
		Utilisateur nouvelUtilisateur = utilisateurService.ajouterUtilisateur(utilisateur);
		return ResponseEntity.status(HttpStatus.CREATED).body(nouvelUtilisateur);
	}

	@PutMapping(path = "/utilisateur/{id}")
	public ResponseEntity<Utilisateur> mettreAJourUtilisateur(@PathVariable(value = "id") Long id_utilisateur,
			@Valid @RequestBody Utilisateur utilisateur) throws Exception {
		Utilisateur utilisateurAModifier = utilisateurService.recupererUtilisateur(id_utilisateur);
		if (utilisateurAModifier == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Mise à jour des attributs obligatoires
		utilisateurAModifier.setId_utilisateur(utilisateur.getId_utilisateur());
		utilisateurAModifier.setNom(utilisateur.getNom());
		utilisateurAModifier.setFonction(utilisateur.getFonction());

		// Mise à jour des attributs non null
		if (utilisateur.getPrenom() != null)
			utilisateurAModifier.setPrenom(utilisateur.getPrenom());
		if (utilisateur.getIdrh() != null)
			utilisateurAModifier.setIdrh(utilisateur.getIdrh());
		// if(utilisateur.getPassword()!=null)
		// utilisateurAModifier.setPassword(utilisateur.getPassword());

		Utilisateur utilisateurMisAJour = utilisateurService.mettreAJourUtilisateur(id_utilisateur, utilisateurAModifier);
		return ResponseEntity.ok(utilisateurMisAJour);
	}

	@DeleteMapping(path = "/utilisateur/{id}")
	public ResponseEntity<Utilisateur> supprimerUtilisateur(@PathVariable(value = "id") Long id_utilisateur) throws Exception {
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(id_utilisateur);
		if (utilisateur == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			utilisateurService.supprimerUtilisateur(id_utilisateur);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}

	
}
