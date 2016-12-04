/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.service.game.exception.ForbiddenMoveException;

/**
 *
 * @author anto
 */
interface Game {
    Board<Case> getPieces();
    EnumPlayer getCurrentPlayer();
    void move(Board<Case> pieces) throws ForbiddenMoveException;
}
