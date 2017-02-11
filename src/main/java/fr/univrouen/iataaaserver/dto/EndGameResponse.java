package fr.univrouen.iataaaserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
