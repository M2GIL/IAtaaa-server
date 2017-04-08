package fr.univrouen.iataaaserver.api.game;

import fr.univrouen.iataaaserver.api.game.player.Player;
import fr.univrouen.iataaaserver.api.game.player.RandomizeCPUPlayer;
import fr.univrouen.iataaaserver.api.game.util.Token;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class GameRunnerImplTest {
    @Test
    public void Launch2RandomIAWithoutException() {
        boolean display = false;

        try {
            Player p1 = new RandomizeCPUPlayer("j1");
            Player p2 = new RandomizeCPUPlayer("j2");
            GameRunner runner = new GameRunnerImpl(new Token("a"), p1, p2, 0);
            runner.getGame().addPropertyChangeListener(Game.EVENT_BOARD_CHANGED, evt -> {

            });
            runner.startGame();
        } catch (Exception e) {
            assertThat(false).isTrue();
        }
    }

}
