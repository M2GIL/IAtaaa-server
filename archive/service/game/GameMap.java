/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.domain.game.GameBean;
import fr.univ.iataaaserver.domain.game.Player;
import java.util.Set;

/**
 *
 * @author anto
 */
public interface GameMap {
    
    boolean addGame(GameBean game);
    boolean addPlayer(String gameId, Player player);
    void clear();
    boolean constains(String id);
    Set<String> getAllId();
    GameBean getGame(String key);
    boolean removeGame(GameBean game);
   
}
