package chatty.training;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton

public class TrainingService {

    @Inject
    private TrainingRepository trainingRepository;


    @Transactional(readOnly = true)
    public List<TrainingEntity> findTrainingByClassifierId(final int classifierId) {
        return trainingRepository.findByClassifierId(classifierId);
    }


    @Transactional(readOnly = true)
    public List<TrainingEntity> findTrainingByAnswerSelectionId(final int answerSelectionId) {
        return trainingRepository.findByAnswerSelectionId(answerSelectionId);
    }
}
