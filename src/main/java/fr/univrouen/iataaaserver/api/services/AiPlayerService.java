package fr.univrouen.iataaaserver.api.services;

import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerDTO;
import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerToCreateDTO;
import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerToUpdateDTO;
import fr.univrouen.iataaaserver.api.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AiPlayerService {

    AiPlayerDTO create(AiPlayerToCreateDTO entityDTO);
    void delete(String id) throws NotFoundException;
    Page<AiPlayerDTO> findAll(Pageable pageable);
    AiPlayerDTO findById(String id) throws NotFoundException;
    AiPlayerDTO findByName(String name) throws NotFoundException;
    AiPlayerDTO update(String id, AiPlayerToUpdateDTO entityDTO) throws NotFoundException;
}
