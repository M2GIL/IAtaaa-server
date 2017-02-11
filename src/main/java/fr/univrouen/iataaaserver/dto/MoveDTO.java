package fr.univrouen.iataaaserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.domain.Case;

import java.io.Serializable;

public class MoveDTO implements Serializable {
    
    @JsonProperty
    private String token;
     @JsonProperty
    private Difficulty difficulty;
    @JsonProperty
    private EnumPlayer player ;
    @JsonProperty
    private Case[] board;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public EnumPlayer getPlayer() {
        return player;
    }

    public void setPlayer(EnumPlayer player) {
        this.player = player;
    }

    public Case[] getBoard() {
        return board;
    }

    public void setBoard(Case[] board) {
        this.board = board;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return "PlayGameBean{" + "token=" + token + ", difficulty=" + difficulty + ", player=" + player + ", board=" + board + '}';
    }
    
    

    
    
}
