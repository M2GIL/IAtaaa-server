/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.services.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author anto
 */
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
