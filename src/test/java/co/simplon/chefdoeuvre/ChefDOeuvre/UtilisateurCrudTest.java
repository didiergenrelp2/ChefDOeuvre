package co.simplon.chefdoeuvre.ChefDOeuvre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import co.simplon.chefdoeuvre.ChefDOeuvreApplication;
import co.simplon.chefdoeuvre.controleur.UtilisateurControleur;
import co.simplon.chefdoeuvre.depot.UtilisateurDepot;
import co.simplon.chefdoeuvre.modele.Utilisateur;
import co.simplon.chefdoeuvre.service.UtilisateurService;

@Transactional
@Rollback(true)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChefDOeuvreApplication.class)
public class UtilisateurCrudTest {
	static Utilisateur utilisateur;
	static Utilisateur utilisateurMaj;
	static UtilisateurService utilisateurService;
	static ResponseEntity<Utilisateur> nouveauUtilisateur;
	static ResponseEntity<Utilisateur> utilisateurSupprime;

	@Autowired
	private UtilisateurControleur utilisateurControleur;
	@Autowired
	private UtilisateurDepot utilisateurDepot;

	@BeforeClass
	public static void initUtilisateur() throws Exception {
		utilisateur = new Utilisateur();
		utilisateurService = new UtilisateurService();
	}
	@Test
	public void testAjouterUtilisateur() {
		try {
			utilisateur = creationUtilisateurTest("Test");
			nouveauUtilisateur = utilisateurControleur.ajouterUtilisateur(utilisateur);
		}
		catch (Exception e) {
			e.printStackTrace();			
		}
		assertTrue(nouveauUtilisateur !=null);
	}

	@Test
	public void testMajUtilisateur() {
		utilisateurMaj = null;
		utilisateur = creationUtilisateurTest("Test");
		try {
			utilisateurMaj = utilisateurDepot.save(utilisateur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(utilisateurMaj != null);
		assertEquals("Test", utilisateurMaj.getNom());
	}
	
	@Test
	public void testSupprimerUtilisateur() {
		try {
			utilisateur = creationUtilisateurTest("Test");
			utilisateurSupprime = utilisateurControleur.ajouterUtilisateur(utilisateur);
			utilisateurSupprime = utilisateurControleur.supprimerUtilisateur((long) 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(utilisateurSupprime.getBody() == null);
	}
	
	private Utilisateur creationUtilisateurTest(String nom) {
		Utilisateur utilisateurTest = new Utilisateur();
		utilisateurTest.setNom(nom);
		utilisateurTest.setId_utilisateur(new Long(1));
		return utilisateurTest;
	}
}
