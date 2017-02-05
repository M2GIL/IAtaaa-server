package fr.univrouen.iataaaserver.player;

import fr.univrouen.iataaaserver.dto.EnumPlayer;
import fr.univrouen.iataaaserver.dto.Difficulty;
import fr.univrouen.iataaaserver.domain.EndGameCase;
import fr.univrouen.iataaaserver.domain.Case;
import fr.univrouen.iataaaserver.domain.Board;
import fr.univrouen.iataaaserver.dto.StatusService;
import fr.univrouen.iataaaserver.util.exception.BusyException;

import java.io.IOException;

public interface Player {
    Difficulty getDifficulty();
    String getName();
    void setDifficulty(Difficulty difficulty);
    StatusService getStatus();
    void startGame(EnumPlayer player) throws BusyException;
    Board<Case> playGame(Board<Case> boardGame, EnumPlayer player) throws IOException, Exception;
    void endGame(EndGameCase endType) throws IOException, Exception;
}
