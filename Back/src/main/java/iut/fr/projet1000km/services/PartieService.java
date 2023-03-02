package iut.fr.projet1000km.services;

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
        Partie partieACreer = new Partie(partie.getNombreJoueur(), partie.getDureeTour(), partie.getCodePartie());
        return partieRepository.saveAndFlush(partieACreer);
    }

    public Partie modifier(Partie partie) {
        Partie partieAModifier = new Partie(partie.getIdPartie(), partie.getNombreJoueur(), partie.getDureeTour(), partie.getCodePartie());
        return partieRepository.saveAndFlush(partieAModifier);
    }
    public void supprimer(Long id){
        partieRepository.deleteById(id);
    }
}

