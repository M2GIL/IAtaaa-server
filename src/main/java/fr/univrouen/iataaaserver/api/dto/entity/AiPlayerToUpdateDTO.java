package fr.univrouen.iataaaserver.api.dto.entity;

import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import fr.univrouen.iataaaserver.api.dto.DTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AiPlayerToUpdateDTO implements DTO {

    @NotNull
    private Difficulty difficulty;
    @NotNull
    private String name;
    @NotNull
    @Pattern(
            regexp="^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",
            message = "Invalid ip"
    )
    private String ip;
    private int port;
    private String path;
}
