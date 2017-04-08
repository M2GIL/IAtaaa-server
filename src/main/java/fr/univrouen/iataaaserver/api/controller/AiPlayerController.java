package fr.univrouen.iataaaserver.api.controller;

import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerDTO;
import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerToCreateDTO;
import fr.univrouen.iataaaserver.api.dto.entity.AiPlayerToUpdateDTO;
import fr.univrouen.iataaaserver.api.dto.params.PageParams;
import fr.univrouen.iataaaserver.api.dto.util.PageableFactory;
import fr.univrouen.iataaaserver.api.exception.NotFoundException;
import fr.univrouen.iataaaserver.api.services.AiPlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/aiPlayers")
public class AiPlayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AiPlayerController.class);

    private AiPlayerService aiPlayerService;

    public AiPlayerController(@Autowired AiPlayerService aiPlayerService) {
        this.aiPlayerService = aiPlayerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AiPlayerDTO create(@Validated @RequestBody AiPlayerToCreateDTO entityToCreateDTO) {
        LOGGER.debug("REST request to create ai player : {}", entityToCreateDTO);
        return aiPlayerService.create(entityToCreateDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) throws NotFoundException {
        LOGGER.debug("REST request to delete ai player : {}", id);
        aiPlayerService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<AiPlayerDTO> findAll(@Valid PageParams pageParams) {
        LOGGER.debug("REST request to find all ai player");
        Pageable pageable = PageableFactory.getPage(pageParams);
        return aiPlayerService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AiPlayerDTO findById(@PathVariable("id") String id) throws NotFoundException {
        LOGGER.debug("REST request to find ai player by id : {}", id);
        return aiPlayerService.findById(id);
    }

    @GetMapping(value = "/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public AiPlayerDTO getByName(@PathVariable("name") String name) throws NotFoundException {
        LOGGER.debug("REST request to find ai player by name : {}", name);
        return aiPlayerService.findByName(name);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AiPlayerDTO update(
            @PathVariable("id") String id,
            @Validated @RequestBody AiPlayerToUpdateDTO entityToUpdateDTO
    ) throws NotFoundException {
        LOGGER.debug("REST request to update ai player : {}", entityToUpdateDTO);
        return aiPlayerService.update(id, entityToUpdateDTO);
    }
}