package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.MainJoueur;
import iut.fr.projet1000km.repository.MainJoueurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainJoueurService {

    private final MainJoueurRepository mainJoueurRepository;

    public MainJoueurService(MainJoueurRepository mainJoueurRepository) {
        this.mainJoueurRepository = mainJoueurRepository;
    }

    public Optional<MainJoueur> getOne(Long id) {
        return mainJoueurRepository.findById(id);
    }

    public List<MainJoueur> getAll() {
        return mainJoueurRepository.findAll();
    }

    public MainJoueur creer(MainJoueur mainJoueur) {
        return mainJoueurRepository.saveAndFlush(mainJoueur);
    }

    public MainJoueur modifier(MainJoueur mainJoueur) {
        return mainJoueurRepository.saveAndFlush(mainJoueur);
    }

    public void supprimer(Long id) {
        mainJoueurRepository.deleteById(id);
    }
}