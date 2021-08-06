package chatty.intents;

import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import chatty.util.AbstractRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.intents.QIntentEntity.intentEntity;

@Singleton
public class IntentRepository extends AbstractRepository<IntentEntity> {
    @Override
    protected Class<IntentEntity> getEntityClass() {
        return IntentEntity.class;
    }


    @Transactional(readOnly = true)
    public Optional<IntentEntity> findByNameAndClassifierId(final String intentName, final int classifierId) {

        return Optional.ofNullable(
                query().select(intentEntity).from(intentEntity).where(
                        intentEntity.intentName.eq(intentName).and(
                                intentEntity.classifierEntity.id.eq(classifierId))).fetchFirst());
    }


    @Transactional(readOnly = true)
    public List<IntentEntity> findByClassifierId(final int classifierId) {
        return query().select(intentEntity).from(intentEntity).where(
                intentEntity.classifierEntity.id.eq(classifierId)).fetch();
    }
}
