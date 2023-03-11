package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Defausse;
import iut.fr.projet1000km.repository.DefausseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefausseService {

    private final DefausseRepository defausseRepository;

    public DefausseService(DefausseRepository defausseRepository) {
        this.defausseRepository = defausseRepository;
    }

    public Optional<Defausse> getOne(Long id) {
        return defausseRepository.findById(id);
    }

    public List<Defausse> getAll() {
        return defausseRepository.findAll();
    }

    public Defausse creer(Defausse defausse) {
        return defausseRepository.saveAndFlush(defausse);
    }

    public Defausse modifier(Defausse defausse) {
        return defausseRepository.saveAndFlush(defausse);
    }

    public void supprimer(Long id) {
        defausseRepository.deleteById(id);
    }
}
