/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.entities.status;

public enum StatusResponse {
    OK, 
    ERROR, 
    INVALIDE_ARGUMENT,  
    PLAYERS_NO_FOUND, 
    NAME_PLAYER_NOT_AVAILABLE, 
    NAME_GAME_NOT_AVAILABLE,
    BUSY_IA;
}
