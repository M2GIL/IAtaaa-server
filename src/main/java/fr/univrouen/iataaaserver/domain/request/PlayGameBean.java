/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.domain.game.Case;

import java.io.Serializable;

/**
 *
 * @author najwa
 */
public class PlayGameBean implements Serializable {
    
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
