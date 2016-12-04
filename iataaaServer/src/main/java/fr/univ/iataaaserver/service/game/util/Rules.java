/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game.util;

import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.service.game.game.Game;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anto
 */
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
        //int[] test = new int[Game.PIECE_SIZE];
        for (int i = 0; i < Game.PIECE_SIZE; ++i) {
            reverseCases[reverseCaseIndice(i)] = pieces[i];
            //test[i] = reverseCaseIndice(i);
        }
        //affichageTableau(test);
        return reverseCases;
    }
}
