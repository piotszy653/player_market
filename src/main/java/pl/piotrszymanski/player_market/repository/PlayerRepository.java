package pl.piotrszymanski.player_market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.piotrszymanski.player_market.model.Player;

import java.util.Set;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
    Set<Player> findAllByIdIn(Iterable<Long> ids);
    Page<Player> findAllByTeam_Id(Long teamId, Pageable pageable);
}
