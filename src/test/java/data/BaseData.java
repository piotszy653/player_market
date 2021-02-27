package data;


import pl.piotrszymanski.player_market.model.BaseEntity;

public class BaseData {

    public static final long defaultId = 1;

    public static <T extends BaseEntity> T withId(T entity) {
        entity.setId(defaultId);
        return entity;
    }
}
