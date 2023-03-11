package iut.fr.projet1000km.models;

public final class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Integer MIN_KM_PARCOURUS = 0;
    public static final Integer MAX_KM_PARCOURUS = 1000;
    public static final Integer MIN_NB_JOUEURS = 2;
    public static final Integer MAX_NB_JOUEURS = 4;

    public static final Integer MIN_DUREE_TOUR = 30;
    public static final Integer MAX_DUREE_TOUR = 90;

}
