package fr.univrouen.iataaaserver.api.services;

import fr.univrouen.iataaaserver.api.dto.entity.CheckersGameDTO;
import fr.univrouen.iataaaserver.api.dto.entity.CheckersGameToCreateDTO;
import fr.univrouen.iataaaserver.api.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CheckersGameService {

    CheckersGameDTO create(CheckersGameToCreateDTO entityDTO);
    void delete(String id) throws NotFoundException;
    Page<CheckersGameDTO> findAll(Pageable pageable);
    CheckersGameDTO findById(String id) throws NotFoundException;
    CheckersGameDTO findByName(String name) throws NotFoundException;
    void start(String gameId) throws NotFoundException;
}
