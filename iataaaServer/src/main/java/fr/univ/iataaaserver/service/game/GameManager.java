/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.service.game.player.HumanPlayer;
import fr.univ.iataaaserver.service.game.player.IAWebServicePlayer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anto
 */
interface GameManager {
    Map<String, GameRunner> getGames();

    List<IAWebServicePlayer> getCpus();

    List<HumanPlayer> getHumans();

    GameRunner getGame(String id);

    HumanPlayer getHuman(String id);

    IAWebServicePlayer getCPU(String id);
}
