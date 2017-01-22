/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.domain.game.Board;
import fr.univrouen.iataaaserver.domain.game.Case;
import fr.univrouen.iataaaserver.domain.request.Response;
import fr.univrouen.iataaaserver.domain.request.GameBean;
import fr.univrouen.iataaaserver.domain.request.PlayerBean;

import java.util.List;
import java.util.Set;

/**
 *
 * @author anto
 */

public interface GamesService {
    
    int TOKEN_SIZE = 20;
    
    Response<GameBean> createGame(GameBean gameBean);
    Set<String> getGameNames();
    Board<Case> getBoard(String gameID);
    Response<PlayerBean> subscribePlayer(PlayerBean player);
    List<String> getPlayersNames();
    List<PlayerBean> getPlayers();
}
