package fr.univrouen.iataaaserver.api.cucumber.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import fr.univrouen.iataaaserver.api.Application;
import fr.univrouen.iataaaserver.api.domain.AiPlayer;
import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
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
public class AiPlayerSteps extends AbstractSteps {

    @Given("^there are these aiPlayers data in database:$")
    public void thereAreTheseAiPlayersDataInDatabase(DataTable aiPlayers) throws Throwable {
        List<Map<String, String>> maps = aiPlayers.asMaps(String.class, String.class);
        maps.forEach(map -> {
            String id = map.get("id");
            Difficulty difficulty = Difficulty.valueOf(map.get("difficulty"));
            String name = map.get("name");
            String token = map.get("token");
            String url = map.get("url");

            persistAiPlayer(id, difficulty, name, token, url);
        });
    }

    @Given("^the aiPlayers data database is:$")
    public void theAiPlayersDatabaseIs(DataTable aiPlayers) throws Throwable {
        List<Map<String, String>> maps = aiPlayers.asMaps(String.class, String.class);

        boolean isCorrectAiPlayerTableSize = aiPlayerTableSizeIs(maps.size());
        assertTrue(isCorrectAiPlayerTableSize);
        aiPlayerTableIsCorrect(maps);
    }

    // PRIVATE
    private void persistAiPlayer(
            String id, Difficulty difficulty, String name, String token, String url
    ) {
        AiPlayer aiPlayer = new AiPlayer();
        aiPlayer.setId(id);
        aiPlayer.setDifficulty(difficulty);
        aiPlayer.setName(name);
        aiPlayer.setToken(token);
        aiPlayer.setUrl(url);

        entityManager.persist(aiPlayer);
    }

    private boolean aiPlayerTableSizeIs(long size) {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(AiPlayer.class)));
        long sizeAiPlayerTable = entityManager.createQuery(cq).getSingleResult();
        return sizeAiPlayerTable == size;
    }

    private void aiPlayerTableIsCorrect(List<Map<String, String>> maps) {
        maps.forEach(map -> {
            String id = map.get("id");
            Difficulty difficulty = Difficulty.valueOf(map.get("difficulty"));
            String name = map.get("name");
            String token = map.get("token");
            String url = map.get("url");

            if (id == null)
                aiPlayerIsInDatabase(difficulty, name, token, url);
            else
                aiPlayerIsInDatabase(id, difficulty, name, token, url);
        });
    }

    private void aiPlayerIsInDatabase(
            Difficulty difficulty, String name, String token, String url
    ) {
        AiPlayer aiPlayer = entityManager.createQuery(
                "SELECT l FROM AiPlayer l WHERE " +
                        "l.difficulty = :difficulty " +
                        "AND l.name = :name " +
                        "AND l.token = :token " +
                        "AND l.url = :url "
                , AiPlayer.class)

                .setParameter("difficulty", difficulty)
                .setParameter("name", name)
                .setParameter("token", token)
                .setParameter("url", url)
                .getSingleResult();
        assertThat(aiPlayer).isNotNull();
    }

    private void aiPlayerIsInDatabase(
            String id, Difficulty difficulty, String name, String token, String url
    ) {
        AiPlayer aiPlayer = entityManager.createQuery(
                "SELECT l FROM AiPlayer l WHERE " +
                        "l.id = :id " +
                        "AND l.difficulty = :difficulty " +
                        "AND l.name = :name " +
                        "AND l.token = :token " +
                        "AND l.url = :url "
                , AiPlayer.class)
                .setParameter("id", id)
                .setParameter("difficulty", difficulty)
                .setParameter("name", name)
                .setParameter("token", token)
                .setParameter("url", url)
                .getSingleResult();
        assertThat(aiPlayer).isNotNull();
    }
}