/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author anto
 */
public class GameBean {
    
    @JsonProperty  
    private String gameID;
    @JsonProperty  
    private String[] players; 

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String[] getPlayers() {
        return players;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }
    
}
