package iut.fr.projet1000km.repository;

import iut.fr.projet1000km.models.Defausse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefausseRepository extends JpaRepository<Defausse, Long> {
}
