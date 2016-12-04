package fr.univ.iataaaserver.service.game.util;

import fr.univ.iataaaserver.service.game.util.*;
import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.service.game.game.Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestUtil {
    private TestUtil() {
        
    }
    
    public static Case[] moveCaseFrom(Case[] board, int sourcePosition, int targetPosition) {
        Case[] result = Arrays.copyOf(board, board.length);
        result[targetPosition] = result[sourcePosition];
        result[sourcePosition] = Case.EMPTY;
        return result;
    }
    
    public static List<Integer> getIACases(Case[] pieces) {
        List<Integer> positions = new ArrayList<>(15);
        for (int i = 0; i < pieces.length; ++i) {
            if (pieces[i] == Case.WHITE_PIECE || pieces[i] == Case.WHITE_QUEEN) {
                positions.add(i);
            }
        }
        return positions;
    }
    
    /**
     * Display the char[][] board
     * Begin left bottom to right top
     *
     * @param boardToDisplay char[][]
     */
    public static void displayBoard(Case[] boardToDisplay) {
        Case[][] board = convertFiftyToHundredBoard(boardToDisplay);

        String line = getLine(board.length);
        String str = line;

        for (int i = board.length - 1; i >= 0; i--) {
            str += "|";
            for (int j = 0; j < board[i].length; j++) {
                // Decal to be VERY beautiful <3
                str += " " ;
                str += board[i][j].getValue() + " |";
            }

            str += "\n" + line;
        }
        System.out.println(str);
    }


    /**
     * Create board with Cases in parameter
     * @param pieces : Integer : nb case / Case : piece type
     * @return
     */
    public static Case[] createBoard(Map.Entry<Integer, Case>... pieces) {
        Case[] board = new Case[Game.PIECE_SIZE];
        for (int i = 0; i < board.length; ++i) {
            board[i] = Case.EMPTY;
        }
        Arrays.asList(pieces).forEach(entry -> {
            board[entry.getKey()] = entry.getValue();
        });
        return board;
    }

    /**
     * Convert the Case[] board in Case[][] board for display
     *
     * @param boardFifty char[]
     * @return Case[][] board
     */
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
    
    /**
     * Get a line of the display
     *
     * @param n number of column
     * @return String
     */
    private static String getLine(int n) {
        String line = "|";
        for (int i = 0; i < n; i++) {
            line += "---|";
        }
        return line + "\n";
    }
}
