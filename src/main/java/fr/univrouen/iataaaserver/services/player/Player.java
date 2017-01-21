package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.domain.request.EnumPlayer;
import fr.univrouen.iataaaserver.domain.request.Difficulty;
import fr.univrouen.iataaaserver.domain.game.EndGameCase;
import fr.univrouen.iataaaserver.domain.game.Case;
import fr.univrouen.iataaaserver.domain.game.Board;
import fr.univrouen.iataaaserver.domain.request.StatusService;
import fr.univrouen.iataaaserver.services.exception.BusyException;

import java.io.IOException;

public interface Player {
    Difficulty getDifficulty();
    String getName();
    void setDifficulty(Difficulty difficulty);
    StatusService getStatus();
    /**
     * @param player
     * @return
     * @throws BusyException 
     */
    void startGame(EnumPlayer player) throws BusyException;
    Board<Case> PlayGame(Board<Case> boardGame, EnumPlayer player) throws IOException, Exception;
    void endGame(EndGameCase endType) throws IOException, Exception;

}
