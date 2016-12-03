package fr.univ.iataaaserver.service.game.util;

import fr.univ.iataaaserver.domain.game.Piece;


public class Util {

    private Util() {}

    /**
     * Display the char[][] board
     * Begin left bottom to right top
     *
     * @param boardToDisplay char[][]
     */
    public static void displayBoard(Piece[] boardToDisplay) {
        Piece[][] board = convertFiftyToHundredBoard(boardToDisplay);

        String line = getLine(board.length);
        String str = line;

        for (int i = board.length - 1; i >= 0; i--) {
            str += "|";
            for (int j = 0; j < board[i].length; j++) {
                // Decal to be VERY beautiful <3
                str += (board[i][j] == Piece.BLACK_PIECE || board[i][j] == Piece.BLACK_QUEEN ) ? "" : " " ;
                str += board[i][j].getValue() + " |";
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
    public static Piece[][] convertFiftyToHundredBoard(Piece[] boardFifty) {
        Piece[][] boardFinal = new Piece[10][10];

        for (int i = 0; i < boardFinal.length; i++) {
            for (int j = 0; j < boardFinal[i].length; j++) {
                boardFinal[i][j] = Piece.EMPTY;
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
