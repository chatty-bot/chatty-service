package chatty.metrics;

import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import chatty.training.TrainingType;
import chatty.util.AbstractRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.metrics.QMetricsEntity.metricsEntity;


@Singleton
public class MetricsRepository extends AbstractRepository<MetricsEntity> {
    @Override
    protected Class<MetricsEntity> getEntityClass() {
        return MetricsEntity.class;
    }


    @Transactional(readOnly = true)
    public List<MetricsEntity> findByClassifierId(final int classifierId) {
        return query().select(metricsEntity).from(metricsEntity).where(
                metricsEntity.classifierId.eq(classifierId)).fetch();
    }


    @Transactional(readOnly = true)
    public Optional<MetricsEntity> findByIntentAndClassifierIdAndTrainingType(final int intentId,
                                                                              final int classifierId,
                                                                              final TrainingType trainingType) {
        return Optional.ofNullable(query().from(metricsEntity).where(
                metricsEntity.intentId.eq(intentId).and(metricsEntity.classifierId.eq(classifierId).and(
                        metricsEntity.trainingType.eq(trainingType)))).fetchOne());
    }
}
