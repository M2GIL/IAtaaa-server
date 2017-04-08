package fr.univrouen.iataaaserver.api.repository;

import fr.univrouen.iataaaserver.api.domain.CheckersGame;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CheckersGameRepository extends PagingAndSortingRepository<CheckersGame, String> {
    CheckersGame findByName(String name);
}
