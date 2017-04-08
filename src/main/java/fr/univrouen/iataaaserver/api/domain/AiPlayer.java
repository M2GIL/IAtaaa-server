package fr.univrouen.iataaaserver.api.domain;

import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@javax.persistence.Entity
public class AiPlayer extends Player {
    @NotNull
    private Difficulty difficulty;
}
