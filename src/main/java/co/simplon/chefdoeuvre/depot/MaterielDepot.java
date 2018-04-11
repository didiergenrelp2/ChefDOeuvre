package co.simplon.chefdoeuvre.depot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.chefdoeuvre.modele.Materiel;

@Repository
public interface MaterielDepot extends JpaRepository<Materiel, Long> {

}
