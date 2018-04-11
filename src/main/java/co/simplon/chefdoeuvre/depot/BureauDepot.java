package co.simplon.chefdoeuvre.depot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.chefdoeuvre.modele.Bureau;

@Repository
public interface BureauDepot extends JpaRepository<Bureau, Long> {

}
