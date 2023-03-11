package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.ZoneDeJeu;
import iut.fr.projet1000km.repository.ZoneDeJeuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneDeJeuService {

    private final ZoneDeJeuRepository zoneDeJeuRepository;

    public ZoneDeJeuService(ZoneDeJeuRepository zoneDeJeuRepository) {
        this.zoneDeJeuRepository = zoneDeJeuRepository;
    }

    public Optional<ZoneDeJeu> getOne(Long id) {
        return zoneDeJeuRepository.findById(id);
    }

    public List<ZoneDeJeu> getAll() {
        return zoneDeJeuRepository.findAll();
    }

    public ZoneDeJeu creer(ZoneDeJeu zoneDeJeu) {

        ZoneDeJeu zoneDeJeuACreer = new ZoneDeJeu(zoneDeJeu.getIdZoneDeJeu());

        return zoneDeJeuRepository.saveAndFlush(zoneDeJeuACreer);
    }

    public ZoneDeJeu modifier(ZoneDeJeu zoneDeJeu) {

        ZoneDeJeu zoneDeJeuAModifier = new ZoneDeJeu(zoneDeJeu.getIdZoneDeJeu());

        return zoneDeJeuRepository.saveAndFlush(zoneDeJeuAModifier);
    }

    public void supprimer(Long id) {
        zoneDeJeuRepository.deleteById(id);
    }
}
