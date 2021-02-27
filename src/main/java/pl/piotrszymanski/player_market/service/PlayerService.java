package pl.piotrszymanski.player_market.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.piotrszymanski.player_market.dto.PlayerDto;
import pl.piotrszymanski.player_market.error.CustomException;
import pl.piotrszymanski.player_market.error.ExceptionCode;
import pl.piotrszymanski.player_market.model.Player;
import pl.piotrszymanski.player_market.repository.PlayerRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository repository;
    private final ModelMapper modelMapper;

    public Page<Player> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Player> getAllByTeam(Long teamId, Pageable pageable) {
        return repository.findAllByTeam_Id(teamId, pageable);
    }

    public Set<Player> getAllByIds(Set<Long> playersIds) {
        return repository.findAllByIdIn(playersIds);
    }

    public Player getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionCode.PLAYER_NOT_FOUND_IN_DB, Long.toString(id)));
    }

    public Player save(Player player) {
        return repository.save(player);
    }

    public Player create(PlayerDto dto) {
        return save(modelMapper.map(dto, Player.class));
    }

    public Player update(PlayerDto dto, Long id) {
        Player player = getById(id);
        modelMapper.map(dto, player);
        return save(player);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
