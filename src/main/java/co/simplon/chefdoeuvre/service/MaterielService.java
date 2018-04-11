package co.simplon.chefdoeuvre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.chefdoeuvre.depot.MaterielDepot;
import co.simplon.chefdoeuvre.modele.Materiel;

@Service
public class MaterielService {
	
	@Autowired
	private MaterielDepot materielDepot;
	
	public Iterable<Materiel> recupererToutMateriel() throws Exception {
		return materielDepot.findAll();
	}
	
	public Materiel recupererMateriel(Long id_materiel) throws Exception {
		return materielDepot.findOne(id_materiel);
	}

	public void supprimerMateriel(Long id_materiel) {
		materielDepot.delete(id_materiel);
	}

	public Materiel ajouterMateriel(Materiel materiel) throws Exception {
		return materielDepot.save(materiel);
	}

	public Materiel miseAJourMateriel(Long id_materiel, Materiel materiel) throws Exception {
		return materielDepot.save(materiel);
	}

	public void supprimerToutMateriel() {
		materielDepot.deleteAll();
		;
	}

}
