package fr.univrouen.iataaaserver.api.util;

import fr.univrouen.iataaaserver.api.game.util.Board;
import fr.univrouen.iataaaserver.api.game.util.Case;

public class DisplayBoard {


    public static void displayBoard(Board<Case> board) {
        displayBoard(board.toArray());
    }
    
    public static void displayBoard(Case[] boardToDisplay) {
        Case[][] board = convertFiftyToHundredBoard(boardToDisplay);

        String line = getLine(board.length);
        String str = line;

        for (int i = 0; i < 10; i++) {
            str += "|";
            for (int j = 0; j < 10; j++) {
                // Decal to be VERY beautiful <3
                str += " " ;
                str += board[i][j].toString() + " |";
            }

            str += "\n" + line;
        }
        System.out.println(str);
    }

    /**
     * Convert the Case[] board in Case[][] board for display
     *
     * @param boardFifty char[]
     * @return Case[][] board
     */
    public static Case[][] convertFiftyToHundredBoard(Case[] boardFifty) {
        Case[][] boardFinal = new Case[10][10];

        for (Case[] boardFinal1 : boardFinal) {
            for (int j = 0; j < boardFinal1.length; j++) {
                boardFinal1[j] = Case.EMPTY;
            }
        }

        for (int i = 0; i < boardFifty.length; i++) {
            int row = (i / 5);
            int column = (((i / 5) + 1) % 2) + (i % 5 * 2);
            boardFinal[row][column] = boardFifty[i];
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

    public static void displayTab(Object[] array) {
        for (int k = 0; k < array.length; ++k) {
            System.out.println(k + " -> " + array[k]);
        }
    }
}
