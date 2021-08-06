package chatty.training;


import java.util.List;
import javax.inject.Singleton;
import chatty.util.AbstractRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.training.QTrainingEntity.trainingEntity;

@Singleton
public class TrainingRepository extends AbstractRepository<TrainingEntity> {
    @Override
    protected Class<TrainingEntity> getEntityClass() {
        return TrainingEntity.class;
    }


    @Transactional(readOnly = true)
    public List<TrainingEntity> findByClassifierId(final int classifierId) {
        return query().from(trainingEntity).where(trainingEntity.classifierId.eq(classifierId)).fetch();
    }


    @Transactional(readOnly = true)
    public List<TrainingEntity> findByAnswerSelectionId(final int answerSelectionId) {
        return query().from(trainingEntity).where(trainingEntity.answerSelectionId.eq(answerSelectionId)).fetch();
    }
}
