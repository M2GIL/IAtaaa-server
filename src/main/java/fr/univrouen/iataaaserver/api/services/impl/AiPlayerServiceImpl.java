package fr.univrouen.iataaaserver.api.services.impl;

import fr.univrouen.iataaaserver.api.domain.AiPlayer;
import fr.univrouen.iataaaserver.api.domain.Player;
import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerDTO;
import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerToCreateDTO;
import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerToUpdateDTO;
import fr.univrouen.iataaaserver.api.dto.mapper.AiPlayerMapper;
import fr.univrouen.iataaaserver.api.exception.NotFoundException;
import fr.univrouen.iataaaserver.api.repository.AiPlayerRepository;
import fr.univrouen.iataaaserver.api.services.AiPlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AiPlayerServiceImpl implements AiPlayerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AiPlayerServiceImpl.class);

    private AiPlayerRepository aiPlayerRepository;

    public AiPlayerServiceImpl(@Autowired AiPlayerRepository aiPlayerRepository) {
        this.aiPlayerRepository = aiPlayerRepository;
    }

    @Override
    public AiPlayerDTO create(AiPlayerToCreateDTO aiPlayerToCreateDTO) {
        AiPlayerDTO result = null;
        AiPlayer aiPlayer = AiPlayerMapper.createDTOToModel(aiPlayerToCreateDTO);
        AiPlayer createdEntity = aiPlayerRepository.save(aiPlayer);
        LOGGER.info("Player is created : {}", createdEntity);
        if (createdEntity != null) {
            result = AiPlayerMapper.modelToDTO(createdEntity);
        }
        return result;
    }

    @Override
    public void delete(String id) throws NotFoundException {
        AiPlayer aiPlayer = aiPlayerRepository.findOne(id);
        if (aiPlayer == null) {
            throw new NotFoundException("id", id, "Player");
        }
        aiPlayerRepository.delete(id);
        LOGGER.info("Player is deleted : {}", aiPlayer);
    }

    @Override
    public Page<AiPlayerDTO> findAll(Pageable pageable) {
        Page<AiPlayer> entitiesPage = aiPlayerRepository.findAll(pageable);
        LOGGER.info("Player is found all");
        List<AiPlayerDTO> entitiesDTO = convertModelIterableToDTOList(entitiesPage.getContent());
        Page<AiPlayerDTO> result = new PageImpl<>(entitiesDTO, pageable, entitiesPage.getTotalElements());
        aiPlayerRepository.findAll(pageable);
        return result;
    }

    @Override
    public AiPlayerDTO findById(String id) throws NotFoundException {
        AiPlayerDTO result;
        AiPlayer aiPlayer = aiPlayerRepository.findOne(id);
        if (aiPlayer == null) {
            throw new NotFoundException("id", id, "Player");
        }
        LOGGER.info("Player is found : {}", aiPlayer);
        result = AiPlayerMapper.modelToDTO(aiPlayer);
        return result;
    }

    @Override
    public AiPlayerDTO findByName(String name) throws NotFoundException {
        AiPlayerDTO result;
        AiPlayer aiPlayer = aiPlayerRepository.findByName(name);
        if (aiPlayer == null) {
            throw new NotFoundException("name", name, "Player");
        }
        LOGGER.info("Player is found : {}", aiPlayer);
        result = AiPlayerMapper.modelToDTO(aiPlayer);
        return result;
    }

    @Override
    public AiPlayerDTO update(String id, AiPlayerToUpdateDTO aiPlayerToUpdateDTO) throws NotFoundException {
        if (!aiPlayerRepository.exists(id)) {
            throw new NotFoundException("id", id, "Player");
        }
        AiPlayer aiPlayer = AiPlayerMapper.updateDTOToModel(aiPlayerToUpdateDTO);
        aiPlayer.setId(id);
        aiPlayer = aiPlayerRepository.save(aiPlayer);
        LOGGER.info("Player is updated : {}", aiPlayer);
        return AiPlayerMapper.modelToDTO(aiPlayer);
    }

    // PRIVATE

    private List<AiPlayerDTO> convertModelIterableToDTOList(Iterable<AiPlayer> players) {
        List<AiPlayerDTO> dtos = new LinkedList<>();
        players.forEach(player -> {
            AiPlayerDTO aiPlayerDTO = AiPlayerMapper.modelToDTO(player);
            dtos.add(aiPlayerDTO);
        });
        return dtos;
    }
}