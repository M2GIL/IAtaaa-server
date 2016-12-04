/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game.game;

import fr.univ.iataaaserver.domain.game.Difficulty;
import fr.univ.iataaaserver.domain.game.EndGameCase;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.service.game.exception.BusyException;
import fr.univ.iataaaserver.service.game.player.Player;

/**
 *
 * @author anto
 */
interface GameRunner {
    EndGameCase getStatus();
    Game getGame();
    Player getPlayer(EnumPlayer player);
    Difficulty getDifficulty(EnumPlayer player);
    void startGame() throws BusyException;
}
