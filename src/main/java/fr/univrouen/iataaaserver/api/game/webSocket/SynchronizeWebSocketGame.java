package fr.univrouen.iataaaserver.api.game.webSocket;

import fr.univrouen.iataaaserver.api.game.GameRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.beans.PropertyChangeEvent;
@Service
public class SynchronizeWebSocketGame {

    @Autowired
    SynchronizeHandlerIA synchronizeHandlerIA;

    public void registerGame(GameRunner game) {
        game.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            switch (evt.getPropertyName()) {
                case GameRunner.EVENT_END_GAME :
                    synchronizeHandlerIA.gameChangement(new WebSocketGameBean((GameRunner) evt.getNewValue()));
                    break;
                case GameRunner.EVENT_NEW_MOVE :
                    synchronizeHandlerIA.gameChangement(new WebSocketGameBean((GameRunner) evt.getNewValue()));
                    break;
            }
        });
    }

}
