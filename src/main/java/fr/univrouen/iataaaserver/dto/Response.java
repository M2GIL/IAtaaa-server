package fr.univrouen.iataaaserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @param <T> 
 */
public class Response<T> {
    
    @JsonProperty
    private T content;
    
    @JsonProperty
    private StatusType status;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Response{" + "content=" + content + ", status=" + status + '}';
    }
    
    
    
    
}
