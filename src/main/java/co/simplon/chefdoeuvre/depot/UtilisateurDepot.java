package co.simplon.chefdoeuvre.depot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.chefdoeuvre.modele.Utilisateur;

@Repository
public interface UtilisateurDepot extends JpaRepository<Utilisateur, Long> {

}
