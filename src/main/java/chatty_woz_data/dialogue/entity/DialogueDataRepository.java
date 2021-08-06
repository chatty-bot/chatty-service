package chatty_woz_data.dialogue.entity;

import javax.inject.Singleton;
import chatty.util.AbstractRepository;
import chatty_woz_data.dialogue.types.DialogueDataEntity;

@Singleton
public class DialogueDataRepository extends AbstractRepository<DialogueDataEntity> {
    @Override
    protected Class<DialogueDataEntity> getEntityClass() {
        return DialogueDataEntity.class;
    }
}
