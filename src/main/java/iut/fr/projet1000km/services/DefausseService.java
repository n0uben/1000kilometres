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
        Defausse defausseACreer = new Defausse(defausse.getIdDefausse());

        return defausseRepository.saveAndFlush(defausseACreer);
    }

    public Defausse modifier(Defausse defausse) {
        Defausse defausseAModifier = new Defausse(defausse.getIdDefausse());

        return defausseRepository.saveAndFlush(defausseAModifier);
    }

    public void supprimer(Long id) {
        defausseRepository.deleteById(id);
    }
}
