/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.entities;

public enum StatusGameCreation {
    OK("No error"), ERROR("Error");
    
    private String message;

    private StatusGameCreation(String message) {
        this.message = message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
}
