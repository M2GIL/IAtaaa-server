/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.service.game.player.WebServicePlayer;
import fr.univ.iataaaserver.service.game.player.Player;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anto
 */

public interface GameManager {
    
    Map<String, GameRunnerImpl> getGames();
    List<WebServicePlayer> getCpus();
    List<Player> getHumans();
    GameRunnerImpl getGame(String id);
    Player getHuman(String id);
    WebServicePlayer getCPU(String id);
}
