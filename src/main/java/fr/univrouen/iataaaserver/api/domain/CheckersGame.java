package fr.univrouen.iataaaserver.api.domain;

import fr.univrouen.iataaaserver.api.domain.util.PlayersNb;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@javax.persistence.Entity
public class CheckersGame extends Entity {

    public CheckersGame() {
        super();
        board = "11111111111111111111000000000033333333333333333333";
        currentPlayer = PlayersNb.PLAYER_1;
    }

    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    @NotEmpty
    private String firstPlayerId;
    @NotNull
    @NotEmpty
    private String secondPlayerId;
    @NotEmpty
    @NotNull
    @Size(min=50, max=50)
    private String board;
    @NotNull
    private PlayersNb currentPlayer;
    private boolean isEnd;
}
