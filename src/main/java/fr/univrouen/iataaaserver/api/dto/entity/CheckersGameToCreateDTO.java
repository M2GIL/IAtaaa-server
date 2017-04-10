package fr.univrouen.iataaaserver.api.dto.entity;

import fr.univrouen.iataaaserver.api.dto.DTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CheckersGameToCreateDTO implements DTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    @Size(min = 1, max = 50)
    private String firstPlayerId;
    @NotNull
    @Size(min = 1, max = 50)
    private String secondPlayerId;
}
