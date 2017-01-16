/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author anto
 */
public class Response<T> {
    
    @JsonProperty
    private T content;
    
    @JsonProperty
    private StatusResponse status;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
    
    
}
