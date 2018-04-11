package co.simplon.chefdoeuvre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.chefdoeuvre.depot.BureauDepot;
import co.simplon.chefdoeuvre.modele.Bureau;

@Service
public class BureauService {

	@Autowired
	private BureauDepot bureauDepot;

	public Iterable<Bureau> recupererTousLesBureaux() throws Exception {
		return bureauDepot.findAll();
	}

	public Bureau recupererBureau(Long id_bureau) throws Exception {
		return bureauDepot.findOne(id_bureau);
	}

	// public void supprimerBureau(Long id_bureau) {
	// bureauDepot.delete(id_bureau);
	// }
	//
	// public Bureau ajouterBureau(Bureau bureau) throws Exception {
	// return bureauDepot.save(bureau);
	// }
	//
	// public Bureau miseAJourBureau(Long id_bureau, Bureau bureau) throws Exception
	// {
	// return bureauDepot.save(bureau);
	// }

	public void supprimerTousLesBureaux() {
		bureauDepot.deleteAll();
		;
	}

}
