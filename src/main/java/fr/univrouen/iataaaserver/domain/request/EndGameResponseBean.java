/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author m_s info
 */
public class EndGameResponseBean {
    
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
