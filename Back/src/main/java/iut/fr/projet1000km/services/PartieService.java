package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Defausse;
import iut.fr.projet1000km.models.Pioche;
import org.springframework.stereotype.Service;
import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.repository.PartieRepository;

import java.util.List;
import java.util.Optional;
@Service
public class PartieService {
    private final PartieRepository partieRepository;

    public PartieService(PartieRepository partieRepository) {
        this.partieRepository = partieRepository;
    }

    public Optional<Partie> getOne(long id) {
        return partieRepository.findById(id);
    }

    public List<Partie> getAll() {
        return partieRepository.findAll();
    }

    public Partie creer(Partie partie) {
        return partieRepository.saveAndFlush(partie);
    }

    public Partie modifier(Partie partie) {
        return partieRepository.saveAndFlush(partie);
    }
    public void supprimer(Long id){
        partieRepository.deleteById(id);
    }
}

