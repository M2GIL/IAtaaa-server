package fr.univ.iataaaserver.service.game.game;

import fr.univ.iataaaserver.service.game.game.GameImpl;
import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.domain.game.EndGameCase;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.service.game.util.Rules;

import java.util.HashMap;
import java.util.LinkedList;


class EndGameAnalyser {
    // ATTRIBUTES

    private LinkedList<Board<Case>> stories = new LinkedList<>();
    private final HashMap<Board<Case>, Integer> count = new HashMap<>();
    private EndGameCase status = EndGameCase.CONTINUE;
    private final int turnWithoutPawnJump = 0;

    // CONSTRUCTOR
    EndGameAnalyser(GameImpl board) {
        board.addPropertyChangeListener(GameImpl.EVENT_BOARD_CHANGED, evt -> {
            if (status != EndGameCase.CONTINUE) {
                // get argument
                Board<Case> newBoard = (Board<Case>) evt.getNewValue();
                Board<Case> oldBoard = (Board<Case>) evt.getOldValue();

                // actualize collection
                stories.add(newBoard);
                incrementCounter(newBoard);

                // is the game finished?
                if (oldBoard != null) verifyEndGame(oldBoard, newBoard);
            }
        });
    }
    // REQUESTS
    public EndGameCase getStatusGame() {
        return status;
    }
    // TOOLS
    private void verifyEndGame(Board<Case> oldBoard, Board<Case> newBoard) {
        EnumPlayer lastPlayer = playerToPlay();

        if (Rules.getAvalaibleMoves(oldBoard, lastPlayer).size() == 0) {
            status = (lastPlayer == EnumPlayer.PLAYER_1 ? EndGameCase.PLAYER_1_VICTORY : EndGameCase.PLAYER_2_VICTORY);
            return;
        }
        if (count.keySet().contains(3)) {
            status = EndGameCase.DRAW;
            return;
        }
        // TODO: 04/12/16 Ajouter les autres cas de draw


        status = EndGameCase.CONTINUE;

    }

    private void incrementCounter(Board<Case> board) {
        Integer actual = count.get(board);
        if (actual == null) {
            count.put(board, 1);
        } else {
            count.put(board, actual + 1);
        }
    }

    private EnumPlayer playerToPlay() {
        return stories.size() % 2 == 0 ? EnumPlayer.PLAYER_1 : EnumPlayer.PLAYER_2;
    }

}
