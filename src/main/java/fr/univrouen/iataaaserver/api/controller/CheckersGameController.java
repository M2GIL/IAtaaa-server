package fr.univrouen.iataaaserver.api.controller;

import fr.univrouen.iataaaserver.api.dto.entity.CheckersGameDTO;
import fr.univrouen.iataaaserver.api.dto.entity.CheckersGameToCreateDTO;
import fr.univrouen.iataaaserver.api.dto.params.PageParams;
import fr.univrouen.iataaaserver.api.dto.util.PageableFactory;
import fr.univrouen.iataaaserver.api.exception.NotFoundException;
import fr.univrouen.iataaaserver.api.services.CheckersGameService;
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
@RequestMapping("api/games/checkers")
public class CheckersGameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckersGameController.class);

    private CheckersGameService checkersGameService;

    public CheckersGameController(@Autowired CheckersGameService checkersGameService) {
        this.checkersGameService = checkersGameService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CheckersGameDTO create(@Validated @RequestBody CheckersGameToCreateDTO entityToCreateDTO) {
        LOGGER.debug("REST request to create ai player : {}", entityToCreateDTO);
        return checkersGameService.create(entityToCreateDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) throws NotFoundException {
        LOGGER.debug("REST request to delete ai player : {}", id);
        checkersGameService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CheckersGameDTO> findAll(@Valid PageParams pageParams) {
        LOGGER.debug("REST request to find all ai player");
        Pageable pageable = PageableFactory.getPage(pageParams);
        return checkersGameService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CheckersGameDTO findById(@PathVariable("id") String id) throws NotFoundException {
        LOGGER.debug("REST request to find ai player by id : {}", id);
        return checkersGameService.findById(id);
    }

    @GetMapping(value = "/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CheckersGameDTO getByName(@PathVariable("name") String name) throws NotFoundException {
        LOGGER.debug("REST request to find ai player by name : {}", name);
        return checkersGameService.findByName(name);
    }

    @GetMapping(value = "/start/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void start(@PathVariable("gameId") String gameId) throws NotFoundException {
        LOGGER.debug("REST request to start game with id : {}", gameId);
        checkersGameService.start(gameId);
    }
}
