package chatty_woz_data.dialogue.entity;

import java.util.Optional;
import javax.inject.Singleton;
import javax.transaction.TransactionScoped;
import chatty.util.AbstractRepository;
import chatty_woz_data.dialogue.types.Issuer;
import chatty_woz_data.dialogue.types.DialogueEntity;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty_woz_data.dialogue.types.QDialogueEntity.dialogueEntity;

@Singleton
public class DialogueRepository extends AbstractRepository<DialogueEntity> {
    @Override
    protected Class<DialogueEntity> getEntityClass() {
        return DialogueEntity.class;
    }


    @TransactionScoped
    public Optional<DialogueEntity> findNotFinishedAndRequiresSystem() {
        return Optional.ofNullable(query().select(dialogueEntity).from(dialogueEntity).where(
                dialogueEntity.isFinished.isFalse().and(dialogueEntity.requiresActionFrom.eq(
                        Issuer.SYSTEM))).fetchFirst());
    }


    @Transactional
    public Optional<DialogueEntity> findNotFinishedAndRequiresUser() {
        return Optional.ofNullable(query().select(dialogueEntity).from(dialogueEntity).where(
                dialogueEntity.isFinished.isFalse().and(dialogueEntity.requiresActionFrom.eq(
                        Issuer.USER))).fetchFirst());

    }
}
