package fr.univrouen.iataaaserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

public class GameDTO {
    
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

    @Override
    public String toString() {
        return "GameBean{" + "gameID=" + gameID + ", players=" + Arrays.toString(players) + '}';
    }   
}
