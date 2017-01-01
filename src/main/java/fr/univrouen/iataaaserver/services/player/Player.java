package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.entities.*;
import fr.univrouen.iataaaserver.entities.status.StatusService;
import fr.univrouen.iataaaserver.services.exception.BusyException;

import java.io.IOException;

public interface Player {
    void setDifficulty(Difficulty difficulty);
    StatusService getStatus();
    /**
     * @param player
     * @return
     * @throws BusyException 
     */
    void startGame(EnumPlayer player) throws BusyException;
    Board<Case> PlayGame(Token game_id,Board<Case> boardGame, EnumPlayer player) throws IOException, Exception;
    void endGame(EndGameCase endType) throws IOException, Exception;

}
