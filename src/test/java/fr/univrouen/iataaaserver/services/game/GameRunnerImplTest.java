package fr.univrouen.iataaaserver.services.game;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.Token;
import fr.univrouen.iataaaserver.services.player.Player;
import fr.univrouen.iataaaserver.services.player.RandomizeCPUPlayer;
import fr.univrouen.iataaaserver.services.util.TestUtil;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;

public class GameRunnerImplTest {
    @Test
    public void Launch2RandomIAWithoutException() {
        boolean display = false;

        try {
            Player p1 = new RandomizeCPUPlayer();
            Player p2 = new RandomizeCPUPlayer();
            GameRunner runner = new GameRunnerImpl(new Token("a"), p1, Difficulty.EASY, p2, Difficulty.EASY);
            runner.getGame().addPropertyChangeListener(Game.EVENT_BOARD_CHANGED, evt -> {
                if (display) TestUtil.displayBoard(((Board<Case>)evt.getNewValue()));
            });
            runner.startGame();
        } catch (Exception e) {
            assertThat(false).isTrue();
        }
    }

}
