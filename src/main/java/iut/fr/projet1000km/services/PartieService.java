package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.repository.PartieRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.logging.Logger;

@Service
public class PartieService {

    private static final int[] LOWERCASE_RANGE = {97, 122};
    private static final int[] UPPERCASE_RANGE = {65, 90};
    private static final int[] NUMBER_RANGE = {48, 57};

    private static final Random RANDOM = new Random();

    private static final Logger LOGGER = Logger.getLogger("PartieService.class");

    private final PartieRepository partieRepository;

    public PartieService(PartieRepository partieRepository) {
        this.partieRepository = partieRepository;
    }

    public static String generateCodePartie(int longueur) {

        StringBuilder code = new StringBuilder();

        for (int i = 0; i < longueur; i++) {
            int randomRangeSelector = RANDOM.nextInt(3);
            int randomCodePoint = switch (randomRangeSelector) {
                case 0 -> RANDOM.nextInt(LOWERCASE_RANGE[1] - LOWERCASE_RANGE[0] + 1) + LOWERCASE_RANGE[0];
                case 1 -> RANDOM.nextInt(UPPERCASE_RANGE[1] - UPPERCASE_RANGE[0] + 1) + UPPERCASE_RANGE[0];
                default -> RANDOM.nextInt(NUMBER_RANGE[1] - NUMBER_RANGE[0] + 1) + NUMBER_RANGE[0];
            };

            code.append((char) randomCodePoint);
        }
        LOGGER.info(code.toString());
        return code.toString();
    }

    public static Partie getPartieOfUserId(Long userId) {
        return new Partie();
    }
}
