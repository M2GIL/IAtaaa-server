package fr.univrouen.iataaaserver.services;

import fr.univrouen.iataaaserver.dto.WebSocketGameBean;
import fr.univrouen.iataaaserver.game.GameRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.beans.PropertyChangeEvent;
import static fr.univrouen.iataaaserver.game.GameRunner.EVENT_END_GAME;
import static fr.univrouen.iataaaserver.game.GameRunner.EVENT_NEW_MOVE;

@Service
public class SynchronizeWebsocketGame {

    @Autowired
    SynchronizeHandlerIA synchronizeHandlerIA;

    public void registerGame(GameRunner game) {
        game.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            switch (evt.getPropertyName()) {
                case EVENT_END_GAME :
                    synchronizeHandlerIA.gameChangement(new WebSocketGameBean((GameRunner) evt.getNewValue()));
                    break;
                case EVENT_NEW_MOVE :
                    synchronizeHandlerIA.gameChangement(new WebSocketGameBean((GameRunner) evt.getNewValue()));
                    break;
            }
        });
    }

}
