package fr.univrouen.iataaaserver.api.game.player;


import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import fr.univrouen.iataaaserver.api.game.exception.BusyException;
import fr.univrouen.iataaaserver.api.game.util.*;

import java.io.IOException;
import java.net.UnknownServiceException;

public interface Player {
    Difficulty getDifficulty();
    String getName();
    void setDifficulty(Difficulty difficulty);
    StatusService getStatus() throws IOException;
    void startGame(EnumPlayer player) throws BusyException, UnknownServiceException;
    Board<Case> playGame(Board<Case> boardGame, EnumPlayer player) throws IOException, Exception;
    void endGame(EndGameCase endType) throws IOException, Exception;
}
