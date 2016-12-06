/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.entities.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author anto
 */
public class GameBean {
    
    @JsonProperty  
    private String gameID;
    @JsonProperty  
    private PlayerBean[] players; 

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public PlayerBean[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerBean[] players) {
        this.players = players;
    }
    
}
