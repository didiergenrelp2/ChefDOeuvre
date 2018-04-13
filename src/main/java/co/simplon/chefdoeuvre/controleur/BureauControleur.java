package co.simplon.chefdoeuvre.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.chefdoeuvre.dao.BureauDAO;
import co.simplon.chefdoeuvre.modele.Bureau;
import co.simplon.chefdoeuvre.modele.Materiel;
import co.simplon.chefdoeuvre.service.BureauService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BureauControleur {

	@Autowired
	private BureauService bureauService;

	@Autowired
	private BureauDAO bureauDAO;

	// SELECT * FROM bureau;
	@GetMapping(path = "/bureaux")
	public @ResponseBody Iterable<Bureau> recupererTousLesBureaux() throws Exception {
		return bureauService.recupererTousLesBureaux();
	}

	// SELECT * FROM bureau WHERE `id_bureau`=id;
	@GetMapping(path = "/bureau/{id}")
	ResponseEntity<Bureau> recupererBureau(@PathVariable(value = "id") long id_bureau) throws Exception {
		Bureau bureau = bureauService.recupererBureau(id_bureau);
		if (bureau == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(bureau);

	}

	@GetMapping(path = "/bureau/{id}/materiels")
	public ResponseEntity<?> recupererMaterielDuBureau(@PathVariable(value = "id") long id_bureau) throws Exception {
		List<Materiel> materiels = null;
		Bureau bureau = bureauService.recupererBureau(id_bureau);
		try {
			materiels = bureauDAO.recupererMaterielDuBureau(id_bureau);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		}
		if (bureau == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(materiels);

	}
}
