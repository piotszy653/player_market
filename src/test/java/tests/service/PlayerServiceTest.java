package tests.service;

import data.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import pl.piotrszymanski.player_market.dto.PlayerDto;
import pl.piotrszymanski.player_market.error.CustomException;
import pl.piotrszymanski.player_market.model.Player;
import pl.piotrszymanski.player_market.repository.PlayerRepository;
import pl.piotrszymanski.player_market.service.PlayerService;

import static mocks.CrudRepoMocks.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static utils.AssertionUtils.recursiveEqualsIgnoringFields;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    PlayerService playerService;
    @Mock
    PlayerRepository repository;
    @Spy
    ModelMapper modelMapper;

    private Player player;
    private Player updatedPlayer;
    private PlayerDto createPlayerDto;
    private PlayerDto updatePlayerDto;

    @BeforeEach
    void init(){
        player = Players.player();
        updatedPlayer = Players.updatedPlayer(player.getUuid());
        createPlayerDto = Players.createPlayerDto();
        updatePlayerDto = Players.updatePlayerDto();
    }

    @Test
    void shouldReturnPlayerById(){
        findByIdMock(repository, player);

        Player result = playerService.getById(player.getId());

        assertEquals(player, result);
        then(repository).shouldHaveNoMoreInteractions();
        then(modelMapper).shouldHaveNoInteractions();
    }

    @Test
    void shouldThrowExceptionWhenGettingNotExistingPlayer(){
        notFoundByIdMock(repository);

        assertThrows(CustomException.class, () -> playerService.getById(player.getId()));
        then(repository).shouldHaveNoMoreInteractions();
        then(modelMapper).shouldHaveNoInteractions();
    }

    @Test
    void shouldCreatePlayer(){
        saveMock(repository);

        Player result = playerService.create(createPlayerDto);

        recursiveEqualsIgnoringFields(player, result, "uuid");
        then(repository).shouldHaveNoMoreInteractions();
        then(modelMapper).should().map(createPlayerDto, Player.class);
        then(modelMapper).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldUpdatePlayer(){
        findByIdMock(repository, player);
        updateMock(repository);

        Player result = playerService.update(updatePlayerDto, player.getId());

        assertEquals(updatedPlayer, result);
        then(repository).shouldHaveNoMoreInteractions();
        then(modelMapper).should().map(updatePlayerDto, player);
        then(modelMapper).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldDeletePlayer(){
        findByIdMock(repository, player);

        playerService.delete(player.getId());

        then(repository).should().delete(player);
        then(repository).shouldHaveNoMoreInteractions();
        then(modelMapper).shouldHaveNoInteractions();
    }

    @Test
    void shouldThrowExceptionWhenDeletingNotExistingPlayer(){
        notFoundByIdMock(repository);

        assertThrows(CustomException.class, () -> playerService.delete(player.getId()));
        then(repository).shouldHaveNoMoreInteractions();
        then(modelMapper).shouldHaveNoInteractions();
    }
}
