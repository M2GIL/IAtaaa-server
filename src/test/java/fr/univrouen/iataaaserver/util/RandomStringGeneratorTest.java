package fr.univrouen.iataaaserver.util;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class RandomStringGeneratorTest {
    
    @Test
    public void nextSessionIdtest() {
        String s = RandomStringGenerator.getRandomString(10);
        assertThat(s.length()).isEqualTo(10);
    }
}
