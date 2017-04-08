package fr.univrouen.iataaaserver.api.dto.entity;

import fr.univrouen.iataaaserver.api.domain.util.PlayersNb;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CheckersGameDTO extends EntityDTO {

    @NotNull
    private String name;
    @NotNull
    private String firstPlayerId;
    @NotNull
    private String secondPlayerId;
    @NotNull
    @Size(min=50, max=50)
    private String board;
    @NotNull
    private PlayersNb currentPlayer;
    private boolean isEnd;
}
