package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.service.game.exception.BusyException;
import fr.univ.iataaaserver.domain.game.Difficulty;
import fr.univ.iataaaserver.domain.game.util.EndGameCase;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.service.game.util.StatusService;
import fr.univ.iataaaserver.service.game.util.Token;

public interface Player {

    StatusService getStatut();
    void startGame(Token idGame, Difficulty difficulty) throws BusyException;
    void PlayGame(Token idToken, Difficulty difficulty, GameImpl boardGame, EnumPlayer player) throws Exception;
    void endGame(Token idToken, String idGame, EndGameCase endType) throws Exception;



}
