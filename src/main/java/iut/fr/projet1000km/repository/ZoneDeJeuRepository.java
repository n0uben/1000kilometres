package iut.fr.projet1000km.repository;

import iut.fr.projet1000km.models.ZoneDeJeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneDeJeuRepository extends JpaRepository<ZoneDeJeu, Long> {
}
