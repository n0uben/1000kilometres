package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.TypeCarte;
import iut.fr.projet1000km.repository.TypeCarteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeCarteService {
    private final TypeCarteRepository typeCarteRepository;

    public TypeCarteService(TypeCarteRepository typeCarteRepository) {
        this.typeCarteRepository = typeCarteRepository;
    }


    public Optional<TypeCarte> getOne(Long id) {
        return typeCarteRepository.findById(id);
    }

    public List<TypeCarte> getAll() {
        return typeCarteRepository.findAll();
    }


    public TypeCarte creer(TypeCarte tcarte) {
        return typeCarteRepository.save(tcarte);
    }

    public TypeCarte modifier(TypeCarte tcarte) {
        return typeCarteRepository.saveAndFlush(tcarte);
    }

    public void supprimer(Long id){
        typeCarteRepository.deleteById(id);
    }
}
