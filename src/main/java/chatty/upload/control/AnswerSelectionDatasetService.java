package chatty.upload.control;

import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.upload.entity.AnswerSelectionDatasetRepository;
import chatty.upload.types.AnswerSelectionDatasetEntity;
import io.micronaut.spring.tx.annotation.Transactional;


@Singleton
public class AnswerSelectionDatasetService {

    @Inject
    private AnswerSelectionDatasetRepository answerSelectionDatasetRepository;
    @Transactional
    public void persist(final AnswerSelectionDatasetEntity answerSelectionDatasetEntity) {
        answerSelectionDatasetRepository.persist(answerSelectionDatasetEntity);
    }
}
