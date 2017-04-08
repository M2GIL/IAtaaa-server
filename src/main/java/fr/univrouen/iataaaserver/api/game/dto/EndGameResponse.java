package fr.univrouen.iataaaserver.api.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.api.game.util.StatusService;

public class EndGameResponse {
    
    @JsonProperty
    private String token;
    @JsonProperty
    private StatusService status;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public StatusService getStatus() {
        return status;
    }

    public void setStatus(StatusService status) {
        this.status = status;
    }  
}
