package fr.univrouen.iataaaserver.game.rules;

import fr.univrouen.iataaaserver.domain.Board;
import fr.univrouen.iataaaserver.domain.Case;
import fr.univrouen.iataaaserver.dto.EnumPlayer;
import fr.univrouen.iataaaserver.game.Game;
import java.util.ArrayList;
import java.util.List;


public class Rules {
    public static List<Board<Case>> getAvailableMoves(Board<Case> board, EnumPlayer p) {
        List<Board<Case>> boards = new ArrayList<>();
        Case[] cases = reverseCases(board.toArray());

        List<Case[]> casesList = ReverseRules.getAvailableMoves(cases, p);
        Board b;
        for(Case[] c : casesList) {
            b = new Board(reverseCases(c));
            boards.add(b);
        }

        return boards;
    }

    private static int reverseCaseIndice(int indice) {
        assert indice >= 0 && indice < 50;
        int column = indice % 5;
        int row = (indice) / 5;
        return (9 - row) * 5 + column;
    }

    private static Case[] reverseCases(Case[] pieces) {
        Case[] reverseCases = new Case[Game.PIECE_SIZE];
        for (int i = 0; i < Game.PIECE_SIZE; ++i) {
            reverseCases[reverseCaseIndice(i)] = pieces[i];
        }
        return reverseCases;
    }
}
