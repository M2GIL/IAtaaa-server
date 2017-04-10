package fr.univrouen.iataaaserver.api.dto.entity;

import fr.univrouen.iataaaserver.api.domain.util.PlayersNb;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CheckersGameDTO extends EntityDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    @Size(min = 1, max = 50)
    private String firstPlayerId;
    @NotNull
    @Size(min = 1, max = 50)
    private String secondPlayerId;
    @NotNull
    @Size(min=50, max=50)
    private String board;
    @NotNull
    private PlayersNb currentPlayer;
    private boolean isEnd;
}
