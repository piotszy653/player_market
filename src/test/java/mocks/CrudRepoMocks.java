package mocks;

import data.BaseData;
import org.springframework.data.repository.CrudRepository;
import pl.piotrszymanski.player_market.model.BaseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class CrudRepoMocks {

    public static <Entity extends BaseEntity, Repo extends CrudRepository<Entity, Long>>
    void findByIdMock(Repo repository, Entity returnedObject) {
        given(repository.findById(returnedObject.getId())).willReturn(Optional.of(returnedObject));
    }

    public static <Repo extends CrudRepository<? extends BaseEntity, Long>>
    void notFoundByIdMock(Repo repository) {
        given(repository.findById(any())).willReturn(Optional.empty());
    }

    public static <Repo extends CrudRepository<? extends BaseEntity, Long>>
    void saveMock(Repo repository) {
        given(repository.save(any())).willAnswer(inv -> BaseData.withId(inv.getArgument(0)));
    }

    public static <Repo extends CrudRepository<? extends BaseEntity, Long>>
    void updateMock(Repo repository) {
        given(repository.save(any())).willAnswer(inv -> inv.getArgument(0));
    }
}
