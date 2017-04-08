package fr.univrouen.iataaaserver.api.game.webSocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.api.domain.util.Difficulty;

public class PlayerDTO {
    
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
        //return token;
        return "toto";
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
