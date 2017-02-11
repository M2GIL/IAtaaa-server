package fr.univrouen.iataaaserver.util;

public class RandomStringGenerator {
    
    private RandomStringGenerator(){};

    public static String getRandomString(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            int random = (int) (Math.random() * 26);
            sb.append(Character.toString((char) (random + 65)));
        }
        return sb.toString();
    }
}
