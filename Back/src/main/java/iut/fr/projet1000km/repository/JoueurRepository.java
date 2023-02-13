package iut.fr.projet1000km.repository;

import iut.fr.projet1000km.models.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurRepository extends JpaRepository<Joueur,Long> {
}
