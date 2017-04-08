package fr.univrouen.iataaaserver.api.dto.entity;

import fr.univrouen.iataaaserver.api.dto.DTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class EntityDTO implements DTO {
    @NotNull
    private String id;
}
