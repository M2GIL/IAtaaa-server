/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.StatusGameCreation;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author anto
 */

public interface GamesService {
    
    StatusGameCreation createGame(String gameID, @RequestBody String iaName, 
         String iaIP1, int iaPort1, String iaIP2, int iaPort2,
         Difficulty difficulty);
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
