package fr.univ.iataaaserver.service.game.game;


import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Difficulty;
import fr.univ.iataaaserver.domain.game.Token;
import fr.univ.iataaaserver.service.game.player.Player;
import fr.univ.iataaaserver.service.game.player.RandomizeCPUPlayer;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class GameRunnerImplTest {
    @Test
    public void Launch2RandomIAWithoutException() {
        boolean display = true;

        try {
            Player p1 = new RandomizeCPUPlayer();
            Player p2 = new RandomizeCPUPlayer();
            GameRunner runner = new GameRunnerImpl(new Token("a"), p1, Difficulty.EASY, p2, Difficulty.EASY);
            runner.getGame().addPropertyChangeListener(Game.EVENT_BOARD_CHANGED, evt -> {
                if (display) displayBoard(((Board<Case>)evt.getNewValue()).toArray());
            });
            runner.startGame();
        } catch (Exception e) {
            assertThat(false).isTrue();
        }
    }




    public static void displayBoard(Case[] boardToDisplay) {
        Case[][] board = convertFiftyToHundredBoard(boardToDisplay);

        String line = getLine(board.length);
        String str = line;

        for (int i = board.length - 1; i >= 0; i--) {
            str += "|";
            for (int j = 0; j < board[i].length; j++) {
                // Decal to be VERY beautiful <3
                str += (board[i][j] == Case.BLACK_PIECE || board[i][j] == Case.BLACK_QUEEN ) ? "" : " " ;
                str += board[i][j].getValue() + " |";
            }

            str += "\n" + line;
        }
        System.out.println(str);
    }
    public static Case[][] convertFiftyToHundredBoard(Case[] boardFifty) {
        Case[][] boardFinal = new Case[10][10];

        for (int i = 0; i < boardFinal.length; i++) {
            for (int j = 0; j < boardFinal[i].length; j++) {
                boardFinal[i][j] = Case.EMPTY;
            }
        }

        for (int i = 0; i < boardFifty.length; i++) {
            boardFinal[(i / 5)][(((i / 5)) % 2) + (i % 5 * 2)] = boardFifty[i];
        }

        return boardFinal;
    }
    private static String getLine(int n) {
        String line = "|";
        for (int i = 0; i < n; i++) {
            line += "---|";
        }
        return line + "\n";
    }
}
