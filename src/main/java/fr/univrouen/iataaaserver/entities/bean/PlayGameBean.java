/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.entities.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.Token;

/**
 *
 * @author najwa
 */
public class PlayGameBean {
    
    @JsonProperty
    private Token token;
     @JsonProperty
    private Difficulty difficulty;
    @JsonProperty
    private String player ;
    @JsonProperty
    private Board<Case> board;
    
    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Board<Case> getBoard() {
        return board;
    }

    public void setBoard(Board<Case> board) {
        this.board = board;
    }
    
    
    
}
