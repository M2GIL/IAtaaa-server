/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game.util;

import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import java.util.List;

/**
 *
 * @author anto
 */
public class Rules {
    public static List<Board<Case>> getAvalaibleMoves(Board<Case> board, EnumPlayer p) {
        return Rules.getAvalaibleMoves(board, p);
    }
}
