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
import co.simplon.chefdoeuvre.controleur.MaterielControleur;
import co.simplon.chefdoeuvre.depot.MaterielDepot;
import co.simplon.chefdoeuvre.modele.Materiel;
import co.simplon.chefdoeuvre.service.MaterielService;

@Transactional
@Rollback(true)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChefDOeuvreApplication.class)
public class MaterielCrudTest {
	static Materiel materiel;
	static Materiel materielMaj;
	static MaterielService materielService;
	static ResponseEntity<Materiel> nouveauMateriel;
	static ResponseEntity<Materiel> materielSupprime;

	@Autowired
	private MaterielControleur materielControleur;
	@Autowired
	private MaterielDepot materielDepot;

	@BeforeClass
	public static void initMateriel() throws Exception {
		materiel = new Materiel();
		materielService = new MaterielService();
	}
	@Test
	public void testAjouterMateriel() {
		try {
			materiel = creationMaterielTest("Lenovo", "PC");
			nouveauMateriel = materielControleur.ajouterMateriel(materiel);
		}
		catch (Exception e) {
			e.printStackTrace();			
		}
		assertTrue(nouveauMateriel !=null);
	}

	@Test
	public void testMajMateriel() {
		materielMaj = null;
		materiel = creationMaterielTest("Lenovo", "PC");
		try {
			materielMaj = materielDepot.save(materiel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(materielMaj != null);
		assertEquals("Lenovo", materielMaj.getMarque());
		assertEquals("PC", materielMaj.getType());
	}
	
	@Test
	public void testSupprimerMateriel() {
		try {
			materiel = creationMaterielTest("Lenovo", "PC");
			materielSupprime = materielControleur.ajouterMateriel(materiel);
			materielSupprime = materielControleur.supprimerMateriel((long) 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(materielSupprime.getBody() == null);
	}
	
	private Materiel creationMaterielTest(String marque, String type) {
		Materiel materielTest = new Materiel();
		materielTest.setMarque(marque);
		materielTest.setType(type);
		materielTest.setId_materiel(new Long(1));
		return materielTest;
	}
}
