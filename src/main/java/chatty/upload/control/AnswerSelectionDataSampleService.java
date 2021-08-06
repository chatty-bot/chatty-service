package chatty.upload.control;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.answer_selection.types.AnswerSelectionDataSampleTO;
import chatty.upload.control.mappers.AnswerSelectionDataSampleMapper;
import chatty.upload.entity.AnswerSelectionDataSampleRepository;
import chatty.upload.types.AnswerSelectionDataSampleEntity;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class AnswerSelectionDataSampleService {

    @Inject
    private AnswerSelectionDataSampleRepository answerSelectionDataSampleRepository;

    @Inject
    private AnswerSelectionDataSampleMapper answerSelectionDataSampleMapper;


    @Transactional
    public void persist(final List<AnswerSelectionDataSampleTO> entities, final int userId) {
        final List<AnswerSelectionDataSampleEntity> answerSelectionDataSampleEntities = answerSelectionDataSampleMapper.toEntity(
                entities, userId);
        answerSelectionDataSampleRepository.batchInsert(answerSelectionDataSampleEntities.iterator(), 500);

    }
}
