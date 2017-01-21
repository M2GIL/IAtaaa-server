/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.controller;

import fr.univrouen.iataaaserver.domain.request.WebSocketGameBean;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static fr.univrouen.iataaaserver.services.game.GameRunner.EVENT_END_GAME;
import static fr.univrouen.iataaaserver.services.game.GameRunner.EVENT_NEW_MOVE;


@Service
public class SynchronizeWebsocketGame {

    @Autowired
    SynchronizeHandlerIA synchronizeHandlerIA;

    public void registerGame(GameRunner game) {
        game.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case EVENT_END_GAME :
                        synchronizeHandlerIA.gameChangement(new WebSocketGameBean((GameRunner) evt.getNewValue()));
                        break;
                    case EVENT_NEW_MOVE :
                        synchronizeHandlerIA.gameChangement(new WebSocketGameBean((GameRunner) evt.getNewValue()));
                        break;
                }
            }
        });
    }

}
