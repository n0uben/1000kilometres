package iut.fr.projet1000km.repository;

import iut.fr.projet1000km.models.Carte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteRepository extends JpaRepository<Carte, Long> {
}
