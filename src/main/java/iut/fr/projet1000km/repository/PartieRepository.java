package iut.fr.projet1000km.repository;

import iut.fr.projet1000km.models.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartieRepository extends JpaRepository<Partie, Long> {
    Optional<Partie> getPartieByCodePartie(String code);

    @Query("SELECT p FROM Partie p JOIN p.joueurs j WHERE j.idUtilisateur = :idUtilisateur")
    Partie findByJoueurId(@Param("idUtilisateur") Long idUtilisateur);

}
