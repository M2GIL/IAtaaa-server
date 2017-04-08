package fr.univrouen.iataaaserver.api.dto.mapper;

import fr.univrouen.iataaaserver.api.domain.AiPlayer;
import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerDTO;
import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerToCreateDTO;
import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerToUpdateDTO;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class AiPlayerMapper {

    private AiPlayerMapper() {
    }

    public static AiPlayer createDTOToModel(AiPlayerToCreateDTO aiPlayerToCreateDTO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade<AiPlayerToCreateDTO, AiPlayer> boundMapper =
                mapperFactory.getMapperFacade(AiPlayerToCreateDTO.class, AiPlayer.class);

        AiPlayer player = boundMapper.map(aiPlayerToCreateDTO);
        return player;
    }

    public static AiPlayer DTOToModel(AiPlayerDTO aiPlayerDTO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade<AiPlayerDTO, AiPlayer> boundMapper =
                mapperFactory.getMapperFacade(AiPlayerDTO.class, AiPlayer.class);

        AiPlayer player = boundMapper.map(aiPlayerDTO);
        return player;
    }

    public static AiPlayerDTO modelToDTO(AiPlayer player) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade<AiPlayer, AiPlayerDTO> boundMapper =
                mapperFactory.getMapperFacade(AiPlayer.class, AiPlayerDTO.class);

        AiPlayerDTO aiPlayerDTO = boundMapper.map(player);
        return aiPlayerDTO;
    }

    public static AiPlayer updateDTOToModel(AiPlayerToUpdateDTO aiPlayerToUpdateDTO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade<AiPlayerToUpdateDTO, AiPlayer> boundMapper =
                mapperFactory.getMapperFacade(AiPlayerToUpdateDTO.class, AiPlayer.class);

        AiPlayer player = boundMapper.map(aiPlayerToUpdateDTO);
        return player;
    }
}
