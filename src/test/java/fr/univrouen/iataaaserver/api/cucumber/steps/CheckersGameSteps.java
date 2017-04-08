package fr.univrouen.iataaaserver.api.cucumber.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import fr.univrouen.iataaaserver.api.Application;
import fr.univrouen.iataaaserver.api.domain.CheckersGame;
import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import fr.univrouen.iataaaserver.api.domain.util.PlayersNb;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = Application.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class CheckersGameSteps extends AbstractSteps {

    @Given("^there are these checkersGames data in database:$")
    public void thereAreTheseCheckersGamesDataInDatabase(DataTable checkersGames) throws Throwable {
        List<Map<String, String>> maps = checkersGames.asMaps(String.class, String.class);
        maps.forEach(map -> {
            String id = map.get("id");
            String name = map.get("name");
            String firstPlayerId = map.get("firstPlayerId");
            String secondPlayerId = map.get("secondPlayerId");
            String board = map.get("board");
            PlayersNb currentPlayer = PlayersNb.valueOf(map.get("currentPlayer"));
            boolean isEnd = Boolean.valueOf(map.get("isEnd"));

            persistCheckersGame(id, name, firstPlayerId, secondPlayerId, board, currentPlayer, isEnd);
        });
    }

    @Given("^the checkersGames data database is:$")
    public void theCheckersGamesDatabaseIs(DataTable checkersGames) throws Throwable {
        List<Map<String, String>> maps = checkersGames.asMaps(String.class, String.class);

        boolean isCorrectCheckersGameTableSize = checkersGameTableSizeIs(maps.size());
        assertTrue(isCorrectCheckersGameTableSize);
        checkersGameTableIsCorrect(maps);
    }

    // PRIVATE
    private void persistCheckersGame(
            String id, String name, String firstPlayerId, String secondPlayerId, String board, PlayersNb currentPlayer, boolean isEnd
    ) {
        CheckersGame checkersGame = new CheckersGame();
        checkersGame.setId(id);
        checkersGame.setName(name);
        checkersGame.setFirstPlayerId(firstPlayerId);
        checkersGame.setSecondPlayerId(secondPlayerId);
        checkersGame.setBoard(board);
        checkersGame.setCurrentPlayer(currentPlayer);
        checkersGame.setEnd(isEnd);

        entityManager.persist(checkersGame);
    }

    private boolean checkersGameTableSizeIs(long size) {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(CheckersGame.class)));
        long sizeCheckersGameTable = entityManager.createQuery(cq).getSingleResult();
        return sizeCheckersGameTable == size;
    }

    private void checkersGameTableIsCorrect(List<Map<String, String>> maps) {
        maps.forEach(map -> {
            String id = map.get("id");
            String name = map.get("name");
            String firstPlayerId = map.get("firstPlayerId");
            String secondPlayerId = map.get("secondPlayerId");
            String board = map.get("board");
            PlayersNb currentPlayer = PlayersNb.valueOf(map.get("currentPlayer"));
            boolean isEnd = Boolean.valueOf(map.get("isEnd"));

            if (id == null)
                checkersGameIsInDatabase(name, firstPlayerId, secondPlayerId, board, currentPlayer, isEnd);
            else
                checkersGameIsInDatabase(id, name, firstPlayerId, secondPlayerId, board, currentPlayer, isEnd);
        });
    }

    private void checkersGameIsInDatabase(
            String name, String firstPlayerId, String secondPlayerId, String board, PlayersNb currentPlayer, boolean isEnd
    ) {
        CheckersGame checkersGame = entityManager.createQuery(
                "SELECT l FROM CheckersGame l WHERE " +
                        "l.name = :name " +
                        "AND l.firstPlayerId = :firstPlayerId " +
                        "AND l.secondPlayerId = :secondPlayerId " +
                        "AND l.board = :board " +
                        "AND l.currentPlayer = :currentPlayer " +
                        "AND l.isEnd = :isEnd "
                , CheckersGame.class)

                .setParameter("name", name)
                .setParameter("firstPlayerId", firstPlayerId)
                .setParameter("secondPlayerId", secondPlayerId)
                .setParameter("board", board)
                .setParameter("currentPlayer", currentPlayer)
                .setParameter("isEnd", isEnd)
                .getSingleResult();
        assertThat(checkersGame).isNotNull();
    }

    private void checkersGameIsInDatabase(
            String id, String name, String firstPlayerId, String secondPlayerId, String board, PlayersNb currentPlayer, boolean isEnd
    ) {
        CheckersGame checkersGame = entityManager.createQuery(
                "SELECT l FROM CheckersGame l WHERE " +
                        "l.id = :id " +
                        "AND l.name = :name " +
                        "AND l.firstPlayerId = :firstPlayerId " +
                        "AND l.secondPlayerId = :secondPlayerId " +
                        "AND l.board = :board " +
                        "AND l.currentPlayer = :currentPlayer " +
                        "AND l.isEnd = :isEnd "
                , CheckersGame.class)
                .setParameter("id", id)
                .setParameter("name", name)
                .setParameter("firstPlayerId", firstPlayerId)
                .setParameter("secondPlayerId", secondPlayerId)
                .setParameter("board", board)
                .setParameter("currentPlayer", currentPlayer)
                .setParameter("isEnd", isEnd)
                .getSingleResult();
        assertThat(checkersGame).isNotNull();
    }
}