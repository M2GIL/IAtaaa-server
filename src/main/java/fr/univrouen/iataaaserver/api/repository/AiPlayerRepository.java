package fr.univrouen.iataaaserver.api.repository;

import fr.univrouen.iataaaserver.api.domain.AiPlayer;
import fr.univrouen.iataaaserver.api.domain.Player;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiPlayerRepository extends PagingAndSortingRepository<AiPlayer, String> {
    AiPlayer findByName(String name);
}
