package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur creer(Utilisateur utilisateur);

    List<Utilisateur> lire();

    Utilisateur modifier(Long id, Utilisateur utilisateur);

    String supprimer(Long id);
}
