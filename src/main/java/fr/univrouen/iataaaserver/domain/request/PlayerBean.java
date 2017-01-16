/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.domain.game.PlayerType;

/**
 *
 * @author anto
 */
public class PlayerBean {
    
    @JsonProperty
    private PlayerType type;
    @JsonProperty
    private String name;
    @JsonProperty
    private String token;
    @JsonProperty
    private String url;
    @JsonProperty
    private Difficulty difficulty;

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    
    
}
