package pl.piotrszymanski.player_market.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.piotrszymanski.player_market.dto.TeamDto;
import pl.piotrszymanski.player_market.model.Team;
import pl.piotrszymanski.player_market.model.TeamConfig;

@Component
@RequiredArgsConstructor
public class TeamMapper {
    private final ModelMapper modelMapper;

    public Team createEntity(TeamDto dto) {
        Team team = modelMapper.map(dto, Team.class);
        TeamConfig config = modelMapper.map(dto, TeamConfig.class);
        team.setConfig(config);
        return team;
    }

    public Team updateEntity(TeamDto dto, Team entity) {
        modelMapper.map(dto, entity);
        modelMapper.map(dto, entity.getConfig());
        return entity;
    }
}
