package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Carte;
import iut.fr.projet1000km.repository.CarteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarteService {

    private final CarteRepository carteRepository;

    public CarteService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    public Optional<Carte> getOne(Long id) {
        return carteRepository.findById(id);
    }

    public List<Carte> getAll() {
        return carteRepository.findAll();
    }


    public Carte creer(Carte carte) {

        Carte carteACreer = new Carte(carte.getIdCarte(), carte.getNom(), carte.getKm(), carte.getEffet(), carte.getNbDispo());

        return carteRepository.saveAndFlush(carteACreer);
    }

    public Carte modifier(Carte carte) {

        Carte carteAModifier = new Carte(carte.getIdCarte(), carte.getNom(), carte.getKm(), carte.getEffet(), carte.getNbDispo());

        return carteRepository.saveAndFlush(carteAModifier);
    }

    public void supprimer(Long id){
        carteRepository.deleteById(id);
    }
}
