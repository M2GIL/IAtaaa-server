package fr.univrouen.iataaaserver.api.dto.mapper;

import fr.univrouen.iataaaserver.api.domain.CheckersGame;
import fr.univrouen.iataaaserver.api.dto.entity.CheckersGameDTO;
import fr.univrouen.iataaaserver.api.dto.entity.CheckersGameToCreateDTO;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class CheckersGameMapper {

    private CheckersGameMapper() {
    }

    public static CheckersGame createDTOToModel(CheckersGameToCreateDTO checkersGameToCreateDTO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade<CheckersGameToCreateDTO, CheckersGame> boundMapper =
                mapperFactory.getMapperFacade(CheckersGameToCreateDTO.class, CheckersGame.class);

        CheckersGame player = boundMapper.map(checkersGameToCreateDTO);
        return player;
    }

    public static CheckersGame DTOToModel(CheckersGameDTO checkersGameDTO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade<CheckersGameDTO, CheckersGame> boundMapper =
                mapperFactory.getMapperFacade(CheckersGameDTO.class, CheckersGame.class);

        CheckersGame player = boundMapper.map(checkersGameDTO);
        return player;
    }

    public static CheckersGameDTO modelToDTO(CheckersGame player) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade<CheckersGame, CheckersGameDTO> boundMapper =
                mapperFactory.getMapperFacade(CheckersGame.class, CheckersGameDTO.class);

        CheckersGameDTO checkersGameDTO = boundMapper.map(player);
        return checkersGameDTO;
    }
}
