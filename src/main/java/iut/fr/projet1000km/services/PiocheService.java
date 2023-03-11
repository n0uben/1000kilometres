package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Pioche;
import iut.fr.projet1000km.repository.PiocheRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PiocheService {

    private final PiocheRepository piocheRepository;

    public PiocheService(PiocheRepository piocheRepository) {
        this.piocheRepository = piocheRepository;
    }

    public Optional<Pioche> getOne(Long id) {
        return piocheRepository.findById(id);
    }

    public List<Pioche> getAll() {
        return piocheRepository.findAll();
    }

    public Pioche creer(Pioche pioche) {
        return piocheRepository.saveAndFlush(pioche);
    }

    public Pioche modifier(Pioche pioche) {
        return piocheRepository.saveAndFlush(pioche);
    }

    public void supprimer(Long id) {
        piocheRepository.deleteById(id);
    }
}
