/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.services.util;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author anto
 */
public class RandomStringGeneratorTest {
    
    @Test
    public void nextSessionIdtest() {
        String s = RandomStringGenerator.getRandomString(10);
        assertThat(s.length()).isEqualTo(10);
    }
}
