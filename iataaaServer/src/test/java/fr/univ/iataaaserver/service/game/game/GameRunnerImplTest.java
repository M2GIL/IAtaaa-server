package fr.univ.iataaaserver.service.game.game;


import fr.univ.iataaaserver.service.game.player.Player;
import fr.univ.iataaaserver.service.game.player.RandomizeCPUPlayer;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class GameRunnerImplTest {
    @Test
    public void Launch2RandomIAWithoutException() {
        try {
            Player p1 = new RandomizeCPUPlayer();
            Player p2 = new RandomizeCPUPlayer();
            GameRunner runner = new GameRunnerImpl(p1, p2);
            runner.startGame();
        } catch (Exception e) {
            assertThat(false).isTrue();
        }
    }
}
