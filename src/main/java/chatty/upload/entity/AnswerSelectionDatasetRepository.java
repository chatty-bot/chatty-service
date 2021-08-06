package chatty.upload.entity;

import javax.inject.Singleton;
import chatty.upload.types.AnswerSelectionDatasetEntity;
import chatty.util.AbstractRepository;

@Singleton
public class AnswerSelectionDatasetRepository extends AbstractRepository<AnswerSelectionDatasetEntity> {
    @Override
    protected Class<AnswerSelectionDatasetEntity> getEntityClass() {
        return AnswerSelectionDatasetEntity.class;
    }
}
