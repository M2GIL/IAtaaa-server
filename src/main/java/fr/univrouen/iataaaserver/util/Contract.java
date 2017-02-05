package fr.univrouen.iataaaserver.util;

/**
 * Class to factorize the usual test of the arguments
 */
public class Contract {
    
    public static void checkArgument(boolean test, String message) {
        if (!test) {
            throw new IllegalArgumentException(message);
        }
    }
}
