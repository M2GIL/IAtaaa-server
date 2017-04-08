package fr.univrouen.iataaaserver.api.dto.entity;

import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AiPlayerDTO extends EntityDTO {
    @NotNull
    private Difficulty difficulty;
    @NotNull
    private String name;
    @NotNull
    private String token;
    @NotNull
    private String url;
}
