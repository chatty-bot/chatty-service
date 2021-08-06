package chatty.upload.entity;

import java.util.List;
import javax.inject.Singleton;
import chatty.upload.types.TextClassificationDatasetEntity;
import chatty.util.AbstractRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.upload.types.QTextClassificationDatasetEntity.textClassificationDatasetEntity;

@Singleton
public class TextClassificationDatasetRepository extends AbstractRepository<TextClassificationDatasetEntity> {
    @Override
    protected Class<TextClassificationDatasetEntity> getEntityClass() {
        return TextClassificationDatasetEntity.class;
    }


    @Transactional(readOnly = true)
    public List<TextClassificationDatasetEntity> findByClassifierId(final int classifierId) {

        return query().from(textClassificationDatasetEntity).where(
                textClassificationDatasetEntity.classifierId.eq(classifierId)).fetch();
    }
}
