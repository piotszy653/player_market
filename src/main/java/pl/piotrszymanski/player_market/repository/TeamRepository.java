package pl.piotrszymanski.player_market.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.piotrszymanski.player_market.model.Team;

import java.util.Set;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
    Set<Team> findAllByPlayers_IdIn(Set<Long> playersIds);
}
