package fr.univ.iataaaserver.service.game.player;

import fr.univ.iataaaserver.service.game.exception.BusyException;
import fr.univ.iataaaserver.domain.game.Difficulty;
import fr.univ.iataaaserver.domain.game.EndGameCase;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.domain.game.StatusService;
import fr.univ.iataaaserver.domain.game.Token;
import fr.univ.iataaaserver.service.game.game.GameImpl;

public interface Player {

    StatusService getStatut();
    void startGame(Token idGame, Difficulty difficulty) throws BusyException;
    void PlayGame(Difficulty difficulty, GameImpl boardGame, EnumPlayer player) throws Exception;
    void endGame(Token idGame, EndGameCase endType) throws Exception;

}
