package chatty.upload.control;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.upload.entity.TextClassificationDatasetRepository;
import chatty.upload.types.TextClassificationDatasetEntity;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class TextClassificationDatasetService {


    @Inject
    private TextClassificationDatasetRepository textClassificationDatasetRepository;


    @Transactional
    public TextClassificationDatasetEntity persist(
            final TextClassificationDatasetEntity textClassificationDatasetEntity) {
        return textClassificationDatasetRepository.persist(textClassificationDatasetEntity);
    }


    @Transactional(readOnly = true)
    public List<TextClassificationDatasetEntity> findDatasetsByClassifierId(final int classifierId) {
        return textClassificationDatasetRepository.findByClassifierId(classifierId);
    }
}
