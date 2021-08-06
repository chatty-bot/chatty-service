package chatty.metrics;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.training.TrainingType;
import io.micronaut.spring.tx.annotation.Transactional;

// TODO
@Singleton

public class MetricsService {

    @Inject
    private MetricsRepository metricsRepository;


    @Transactional(readOnly = true)
    public List<MetricsEntity> findMetricsByClassifierId(final int classifierId) {
        return metricsRepository.findByClassifierId(classifierId);
    }


    @Transactional
    public void updateMetric(final MetricsEntity metricsEntity) {
        metricsRepository.merge(metricsEntity);
    }


    @Transactional
    public void createMetric(final MetricsEntity metricsEntity) {
        metricsRepository.persist(metricsEntity);
    }


    @Transactional(readOnly = true)
    public Optional<MetricsEntity> findByIntentAndClassifierAndTrainingType(final int intentId,
                                                                            final int classifierId,
                                                                            final TrainingType trainingType) {
        return metricsRepository.findByIntentAndClassifierIdAndTrainingType(intentId, classifierId, trainingType);
    }
}
