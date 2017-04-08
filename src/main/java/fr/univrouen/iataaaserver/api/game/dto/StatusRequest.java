package fr.univrouen.iataaaserver.api.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusRequest {
    @JsonProperty
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
