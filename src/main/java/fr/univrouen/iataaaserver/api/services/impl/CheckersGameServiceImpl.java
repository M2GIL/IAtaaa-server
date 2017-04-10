package fr.univrouen.iataaaserver.api.services.impl;

import fr.univrouen.iataaaserver.api.domain.AiPlayer;
import fr.univrouen.iataaaserver.api.domain.CheckersGame;
import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import fr.univrouen.iataaaserver.api.dto.entity.CheckersGameDTO;
import fr.univrouen.iataaaserver.api.dto.entity.CheckersGameToCreateDTO;
import fr.univrouen.iataaaserver.api.dto.mapper.CheckersGameMapper;
import fr.univrouen.iataaaserver.api.exception.NotFoundException;
import fr.univrouen.iataaaserver.api.game.GameRunner;
import fr.univrouen.iataaaserver.api.game.GameRunnerImpl;
import fr.univrouen.iataaaserver.api.game.player.Player;
import fr.univrouen.iataaaserver.api.game.player.WebServicePlayer;
import fr.univrouen.iataaaserver.api.game.util.Token;
import fr.univrouen.iataaaserver.api.game.webSocket.SynchronizeWebSocketGame;
import fr.univrouen.iataaaserver.api.repository.AiPlayerRepository;
import fr.univrouen.iataaaserver.api.repository.CheckersGameRepository;
import fr.univrouen.iataaaserver.api.services.CheckersGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CheckersGameServiceImpl implements CheckersGameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckersGameServiceImpl.class);

    private SynchronizeWebSocketGame synchronizeWebSocketGame;
    private CheckersGameRepository checkerGameRepository;
    private AiPlayerRepository aiPlayerRepository;

    public CheckersGameServiceImpl(
            @Autowired CheckersGameRepository checkerGameRepository,
            @Autowired AiPlayerRepository aiPlayerRepository,
            @Autowired SynchronizeWebSocketGame synchronizeWebSocketGame
            ) {
        this.checkerGameRepository = checkerGameRepository;
        this.aiPlayerRepository = aiPlayerRepository;
        this.synchronizeWebSocketGame = synchronizeWebSocketGame;
    }

    @Override
    public CheckersGameDTO create(CheckersGameToCreateDTO checkersGameToCreateDTO) {
        CheckersGame checkersGame = CheckersGameMapper.createDTOToModel(checkersGameToCreateDTO);
        CheckersGame createdEntity = checkerGameRepository.save(checkersGame);
        LOGGER.info("Checkers game is created : {}", createdEntity);
        return CheckersGameMapper.modelToDTO(createdEntity);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        CheckersGame checkersGame = Optional.ofNullable(checkerGameRepository.findOne(id))
                .orElseThrow(() -> new NotFoundException("id", id, "Checkers game"));
        checkerGameRepository.delete(id);
        LOGGER.info("Checkers game is deleted : {}", checkersGame);
    }

    @Override
    public Page<CheckersGameDTO> findAll(Pageable pageable) {
        Page<CheckersGame> entitiesPage = checkerGameRepository.findAll(pageable);
        LOGGER.info("Checkers game is found all");
        List<CheckersGameDTO> entitiesDTO = convertModelIterableToDTOList(entitiesPage.getContent());
        return new PageImpl<>(entitiesDTO, pageable, entitiesPage.getTotalElements());
    }

    @Override
    public CheckersGameDTO findById(String id) throws NotFoundException {
        CheckersGame checkersGame = Optional.ofNullable(checkerGameRepository.findOne(id))
                .orElseThrow(() -> new NotFoundException("id", id, "Checkers game"));
        LOGGER.info("Checkers game is found : {}", checkersGame);
        return CheckersGameMapper.modelToDTO(checkersGame);
    }

    @Override
    public CheckersGameDTO findByName(String name) throws NotFoundException {
        CheckersGame checkersGame = Optional.ofNullable(checkerGameRepository.findByName(name))
                .orElseThrow(() -> new NotFoundException("name", name, "Checkers game"));
        LOGGER.info("Checkers game is found : {}", checkersGame);
        return CheckersGameMapper.modelToDTO(checkersGame);
    }

    @Override
    public void start(String gameId) throws NotFoundException {
        CheckersGame checkersGame = checkerGameRepository.findOne(gameId);
        if (checkersGame == null) {
            throw new NotFoundException("id", gameId, "Checkers game");
        }
        startGame(checkersGame);
        LOGGER.info("Start game : {}", checkersGame);
    }

    // PRIVATE

    private List<CheckersGameDTO> convertModelIterableToDTOList(Iterable<CheckersGame> players) {
        List<CheckersGameDTO> dtos = new LinkedList<>();
        players.forEach(player -> {
            CheckersGameDTO CheckersGameDTO = CheckersGameMapper.modelToDTO(player);
            dtos.add(CheckersGameDTO);
        });
        return dtos;
    }

    private void startGame(CheckersGame checkersGame) {

        String gameId = checkersGame.getId();
        AiPlayer firstPlayer = aiPlayerRepository.findOne(checkersGame.getFirstPlayerId());
        AiPlayer secondPlayer = aiPlayerRepository.findOne(checkersGame.getSecondPlayerId());


        String firstPlayerName = firstPlayer.getName();
        String firstPlayerUrl = getUrl(firstPlayer.getIp(), firstPlayer.getPort(), firstPlayer.getPath());
        Difficulty firstPlayerDifficulty = firstPlayer.getDifficulty();
        String firstPlayerToken = firstPlayer.getToken();

        String secondPlayerName = secondPlayer.getName();
        String secondPlayerUrl = getUrl(secondPlayer.getIp(), secondPlayer.getPort(), secondPlayer.getPath());
        Difficulty secondPlayerDifficulty = secondPlayer.getDifficulty();
        String secondPlayerToken = secondPlayer.getToken();

        Token tokenGame = new Token(gameId);

        Player player1 = new WebServicePlayer(firstPlayerName, firstPlayerToken, firstPlayerUrl, firstPlayerDifficulty);
        Player player2 = new WebServicePlayer(secondPlayerName, secondPlayerToken, secondPlayerUrl, secondPlayerDifficulty);

        GameRunner gr = new GameRunnerImpl(tokenGame, player1, player2);
        synchronizeWebSocketGame.registerGame(gr);

        Runnable runGame = () -> {
            try {
                gr.startGame();
            } catch (Exception ignored) {
                gr.abort();
            }
        };
        new Thread(runGame).start();
    }

    // PRIVATE

    private String getUrl(String ip, int port, String path) {
        return ip + ":" + port + "/" + path;
    }
}