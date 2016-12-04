package fr.univ.iataaaserver.service.game.game;

import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.domain.game.EndGameCase;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.service.game.util.Rules;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


class EndGameAnalyser {
    // ATTRIBUTES

    private LinkedList<Board<Case>> stories = new LinkedList<>();
    private final Map<EnumPlayer, Map<Board<Case>, Integer>> countBoardByPlayer;
    private EndGameCase status = EndGameCase.CONTINUE;
    private int turnWithoutPawnMoveOrPawnTake = 0;
    private int turnWithConfiguration3v1 = 0;
    private boolean lastTurn = false;

    // CONSTRUCTOR
    EndGameAnalyser(GameImpl board) {
        countBoardByPlayer = new HashMap<>();
        countBoardByPlayer.put(EnumPlayer.PLAYER_1, new HashMap<>());
        countBoardByPlayer.put(EnumPlayer.PLAYER_2, new HashMap<>());

        board.addPropertyChangeListener(GameImpl.EVENT_BOARD_CHANGED, evt -> {
            if (status == EndGameCase.CONTINUE) {
                // get argument
                Board<Case> newBoard = (Board<Case>) evt.getNewValue();
                Board<Case> oldBoard = (Board<Case>) evt.getOldValue();

                // actualize collection
                stories.add(newBoard);
                incrementCounter(playerToPlay(), newBoard);

                if (aPawnHasMovedOrAPieceHasBeenTaken(oldBoard, newBoard)) {
                    turnWithoutPawnMoveOrPawnTake = 0;
                } else {
                    ++turnWithoutPawnMoveOrPawnTake;
                }
                if (turnWithConfiguration3v1 != 0) {
                    ++turnWithConfiguration3v1;
                } else {
                    if (isTheConfiguration3v1(newBoard)) {
                        turnWithConfiguration3v1 = 1;
                    }
                }
                // is the game finished?
                verifyEndGame(oldBoard, newBoard);

                if (lastTurnWithRemainedPiece(newBoard)) {
                    lastTurn = true;
                }
            }
        });
    }

    private boolean lastTurnWithRemainedPiece(Board<Case> newBoard) {
        int[] count = countPiece(newBoard);
        return !(count[Case.WHITE_PIECE.ordinal()] != 0 || count[Case.BLACK_PIECE.ordinal()] != 0) && ((count[Case.WHITE_QUEEN.ordinal()] == 1 && count[Case.BLACK_QUEEN.ordinal()] <= 2) || (count[Case.BLACK_QUEEN.ordinal()] == 1 && count[Case.WHITE_QUEEN.ordinal()] <= 2));
    }

    private boolean isTheConfiguration3v1(Board<Case> newBoard) {
        int[] count = countPiece(newBoard);
        if (count[Case.BLACK_PIECE.ordinal()] == 0 && count[Case.BLACK_QUEEN.ordinal()] == 1
            && (count[Case.WHITE_QUEEN.ordinal()] + count[Case.WHITE_PIECE.ordinal()]) == 3) {
            return true;
        }
        if (count[Case.WHITE_PIECE.ordinal()] == 0 && count[Case.WHITE_QUEEN.ordinal()] == 1
            && (count[Case.BLACK_QUEEN.ordinal()] + count[Case.BLACK_PIECE.ordinal()]) == 3) {
            return true;
        }
        return false;
    }

    private boolean aPawnHasMovedOrAPieceHasBeenTaken(Board<Case> oldBoard, Board<Case> newBoard) {
        int[] oldCount = countPiece(oldBoard);
        int[] newCount = countPiece(newBoard);
        if (oldCount[Case.EMPTY.ordinal()] != newCount[Case.EMPTY.ordinal()]) {
            return true;
        }
        Case[] oldArray = oldBoard.toArray();
        Case[] newArray = newBoard.toArray();
        int k = 0;
        while(k < oldArray.length && oldArray[k] == newArray[k]) {
            ++k;
        }
        return oldArray[k] == Case.BLACK_PIECE || oldArray[k] == Case.WHITE_PIECE
            || newArray[k] == Case.BLACK_PIECE || newArray[k] == Case.WHITE_PIECE;
    }

    // REQUESTS
    public EndGameCase getStatusGame() {
        return status;
    }
    // TOOLS
    private void verifyEndGame(Board<Case> oldBoard, Board<Case> newBoard) {
        if (oldBoard == null) {
            System.out.println("null");
            return;
        }
        EnumPlayer lastPlayer = playerToPlay();

        // Impossibilité de jouer ==> Victoire adversaire
        if (Rules.getAvailableMoves(oldBoard, lastPlayer).size() == 0) {
            status = (lastPlayer == EnumPlayer.PLAYER_1 ? EndGameCase.PLAYER_1_VICTORY : EndGameCase.PLAYER_2_VICTORY);
            return;
        }

        // Troisième occurence d'un plateau => DRAW
        if (countBoardByPlayer.get(EnumPlayer.PLAYER_1).values().contains(3)
            || countBoardByPlayer.get(EnumPlayer.PLAYER_2).values().contains(3)) {
            status = EndGameCase.DRAW;
            return;
        }

        int[] countPiece = countPiece(newBoard);
        // Plus de pièce J2 ==> Victoire J1
        if (countPiece[Case.BLACK_PIECE.ordinal()] == 0  && countPiece[Case.BLACK_QUEEN.ordinal()] == 0) {
            status = EndGameCase.PLAYER_1_VICTORY;
            return;
        }
        // Plus de pièce J1 ==> Victoire J2
        if (countPiece[Case.WHITE_PIECE.ordinal()] == 0  && countPiece[Case.WHITE_QUEEN.ordinal()] == 0) {
            status = EndGameCase.PLAYER_2_VICTORY;
            return;
        }

        if (turnWithoutPawnMoveOrPawnTake == 25)  {
            status = EndGameCase.DRAW;
            return;
        }

        if (turnWithConfiguration3v1 == 32) {
            status = EndGameCase.DRAW;
            return;
        }

        if (lastTurn) {
            status = EndGameCase.DRAW;
            return;
        }

        status = EndGameCase.CONTINUE;
    }

    private void incrementCounter(EnumPlayer enumPlayer, Board<Case> board) {
        Map<Board<Case>, Integer> map = countBoardByPlayer.get(enumPlayer);
        Integer actual = map.get(board);
        if (actual == null) {
            map.put(board, 1);
        } else {
            map.put(board, actual + 1);
        }
    }

    private int[] countPiece(Board<Case> board) {
        int[] result = new int[]{0,0,0,0,0};
        for (Case c : board.toArray()) {
            ++result[c.ordinal()];
        }
        return result;
    }

    private EnumPlayer playerToPlay() {
        return stories.size() % 2 == 0 ? EnumPlayer.PLAYER_1 : EnumPlayer.PLAYER_2;
    }

}
