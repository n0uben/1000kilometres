package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Carte;
import iut.fr.projet1000km.models.Pioche;
import iut.fr.projet1000km.repository.CarteRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PiocheService {
    private final CarteRepository carteRepository;

    public PiocheService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    public Pioche initialise(Pioche pioche){

        List<Carte> cartes = carteRepository.findAll();
        cartes.forEach(carte -> {
            for(int i=0;i<carte.getNbDispo();i++){
                pioche.getCartes().add(carte);
            }
        });
        //mélange la pioche
        List<Carte> toShuffle = pioche.getCartes();
        Collections.shuffle(toShuffle);
        pioche.setCartes(toShuffle);
        return pioche;
    }
}
