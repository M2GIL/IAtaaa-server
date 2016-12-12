/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.bean.GameBean;
import fr.univrouen.iataaaserver.entities.status.StatusResponse;
import fr.univrouen.iataaaserver.entities.bean.PlayerBean;
import java.util.Set;

/**
 *
 * @author anto
 */

public interface GamesService {
    
    int TOKEN_SIZE = 10;
    
    StatusResponse createGame(GameBean gameBean);
    Set<String> getGameNames();
    Board<Case> getBoard(String gameID);
    
    StatusResponse subscribePlayer(PlayerBean player);

}
