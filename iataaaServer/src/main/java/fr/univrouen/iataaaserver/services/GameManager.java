/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.entities.Board;
import java.util.Set;

/**
 *
 * @author anto
 */

public interface GameManager {
    
    boolean createGame(String id);
    Set<String> getGameNames();
    Board getBoard(String gameID);
    
    /*
    Map<String, GameRunner> getGames();
    List<WebServicePlayer> getCpus();
    List<Player> getHumans();
    GameRunner getGame(String id);
    Player getHuman(String id);
    WebServicePlayer getCPU(String id);
    */
}
