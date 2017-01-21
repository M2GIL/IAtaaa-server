package fr.univrouen.iataaaserver.services.game;

import fr.univrouen.iataaaserver.domain.game.Board;
import fr.univrouen.iataaaserver.domain.game.Case;
import fr.univrouen.iataaaserver.domain.game.Token;
import fr.univrouen.iataaaserver.services.player.Player;
import fr.univrouen.iataaaserver.services.player.RandomizeCPUPlayer;
import fr.univrouen.iataaaserver.services.util.TestUtil;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class GameRunnerImplTest {
    @Test
    public void Launch2RandomIAWithoutException() {
        boolean display = true;

        try {
            Player p1 = new RandomizeCPUPlayer("j1");
            Player p2 = new RandomizeCPUPlayer("j2");
            GameRunner runner = new GameRunnerImpl(new Token("a"), p1, p2);
            runner.getGame().addPropertyChangeListener(Game.EVENT_BOARD_CHANGED, evt -> {
                if (display) TestUtil.displayBoard(((Board<Case>)evt.getNewValue()));
            });
            runner.startGame();
        } catch (Exception e) {
            assertThat(false).isTrue();
        }
    }

}
