/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.entities.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.entities.Token;
import fr.univrouen.iataaaserver.entities.status.StatusService;

/**
 *
 * @author m_s info
 */
public class EndGameBean {
    
    @JsonProperty
    private Token token;
    @JsonProperty
    private StatusService status;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public StatusService getStatus() {
        return status;
    }

    public void setStatus(StatusService status) {
        this.status = status;
    }
    
    
    
}
