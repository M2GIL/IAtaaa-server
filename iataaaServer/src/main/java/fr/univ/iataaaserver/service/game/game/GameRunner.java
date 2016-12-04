/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game.game;

import fr.univ.iataaaserver.service.game.exception.BusyException;

/**
 *
 * @author anto
 */
interface GameRunner {
    boolean isFinished();
    void startGame() throws BusyException;
}
