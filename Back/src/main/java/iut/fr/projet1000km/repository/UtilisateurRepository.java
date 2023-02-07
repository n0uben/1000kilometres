package iut.fr.projet1000km.repository;

import iut.fr.projet1000km.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
