package co.simplon.chefdoeuvre.ChefDOeuvre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;

import javax.transaction.Transactional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import co.simplon.chefdoeuvre.controleur.MaterielControleur;
import co.simplon.chefdoeuvre.depot.MaterielDepot;
import co.simplon.chefdoeuvre.modele.Materiel;
import co.simplon.chefdoeuvre.service.MaterielService;

@Transactional
@Rollback(true)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChefDOeuvreApplicationTests.class)
public class MaterielCrudTest {
	static Materiel materiel;
	static Materiel materielMaj;
	static MaterielService materielService;
	static Materiel nouveauMateriel;
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
			materiel = creationMock("Lenovo", "PC");
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
		materiel = creationMock("Lenovo", "PC");
		try {
			materielMaj = materielDepot.save(materiel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(materielMaj != null);
		assertEquals("Lenovo", materielMaj.getMarque());
		assertEquals("PC", materielMaj.getType());
	}
	
//	@Test
//	public void testSupprimerMateriel() {
//		try {
//			materiel = creationMock("Lenovo", "PC");
//			materielSupprime = materielControleur.ajouterMateriel(materiel);
//			materielSupprime = materielControleur.supprimerMateriel((long) 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		assertTrue(materielSupprime.getBody() == null);
//	}
	
	private Materiel creationMock(String marque, String type) {
		Materiel mock = new Materiel();
		mock.setMarque(marque);
		mock.setType(type);
		mock.setId_materiel(new Long(1));
		return mock;
	}
}
