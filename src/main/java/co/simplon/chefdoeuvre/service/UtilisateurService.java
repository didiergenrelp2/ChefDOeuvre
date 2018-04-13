package co.simplon.chefdoeuvre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.chefdoeuvre.depot.UtilisateurDepot;
import co.simplon.chefdoeuvre.modele.Utilisateur;

@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurDepot utilisateurDepot;
	
	public Iterable<Utilisateur> recupererTousLesUtilisateurs() throws Exception {
		return utilisateurDepot.findAll();
	}
	
	public Utilisateur recupererUtilisateur(Long id_utilisateur) throws Exception {
		return utilisateurDepot.findOne(id_utilisateur);
	}

	public void supprimerUtilisateur(Long id_utilisateur) {
		utilisateurDepot.delete(id_utilisateur);
	}

	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) throws Exception {
		return utilisateurDepot.save(utilisateur);
	}

	public Utilisateur mettreAJourUtilisateur(Long id_utilisateur, Utilisateur utilisateur) throws Exception {
		return utilisateurDepot.save(utilisateur);
	}

	public void supprimerTousLesUtilisateurs() {
		utilisateurDepot.deleteAll();
		;
	}

}
