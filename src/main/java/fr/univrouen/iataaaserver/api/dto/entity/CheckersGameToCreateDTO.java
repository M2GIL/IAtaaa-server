package fr.univrouen.iataaaserver.api.dto.entity;

import fr.univrouen.iataaaserver.api.dto.DTO;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CheckersGameToCreateDTO implements DTO {

    @NotNull
    private String name;
    @NotNull
    private String firstPlayerId;
    @NotNull
    private String secondPlayerId;
}
