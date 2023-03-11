package iut.fr.projet1000km.repository;

import iut.fr.projet1000km.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByPseudoAndMotDePasse(String pseudo, String motDePasse);
}
