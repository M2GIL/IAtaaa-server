package fr.univrouen.iataaaserver.api.dto.entity;

import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import fr.univrouen.iataaaserver.api.dto.DTO;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class AiPlayerToCreateDTO implements DTO {

    @NotNull
    private Difficulty difficulty;
    @NotNull
    private String name;
    @NotNull
    private String url;
}
