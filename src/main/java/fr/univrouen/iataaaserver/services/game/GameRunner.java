/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.services.game;

import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.EndGameCase;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.entities.util.observable.Observable;
import fr.univrouen.iataaaserver.services.player.Player;



/**
 *
 * @author anto
 */
public interface GameRunner extends Observable{
    String EVENT_NEW_MOVE = "event new move";
    String EVENT_END_GAME = "party over";
    String EVENT_START_GAME = "starting block";

    EndGameCase getStatus();
    Game getGame();
    Player getPlayer(EnumPlayer player);
    Difficulty getDifficulty(EnumPlayer player);
    void startGame() throws BusyException;
}
