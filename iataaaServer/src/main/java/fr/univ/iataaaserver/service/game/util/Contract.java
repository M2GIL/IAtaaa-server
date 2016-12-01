package fr.univ.iataaaserver.service.game.util;

/**
 * Class to factorize the usual test of the arguments
 */
public class Contract {
    
    private Contract() {
    }
    
    public static void checkArgument(boolean test, String message) {
        if (!test) {
            throw new IllegalArgumentException(message);
        }
    }
}
