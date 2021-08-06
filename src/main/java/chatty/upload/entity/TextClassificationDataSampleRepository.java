package chatty.upload.entity;

import java.util.Iterator;
import java.util.List;
import javax.inject.Singleton;
import chatty.upload.types.TextClassificationDataSampleEntity;
import chatty.util.AbstractRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.upload.types.QTextClassificationDataSampleEntity.textClassificationDataSampleEntity;

@Singleton
public class TextClassificationDataSampleRepository extends AbstractRepository<TextClassificationDataSampleEntity> {
    @Override
    protected Class<TextClassificationDataSampleEntity> getEntityClass() {
        return TextClassificationDataSampleEntity.class;
    }


    @Transactional
    public void batchInsert(Iterator<TextClassificationDataSampleEntity> dataSource, final int batchSize) {
        int count = 0;
        while (dataSource.hasNext()) {
            TextClassificationDataSampleEntity entity = dataSource.next();
            persist(entity);
            count++;
            if (count % batchSize == 0) {
                getEntityManager().flush();
                getEntityManager().clear();

            }
        }

    }


    @Transactional(readOnly = true)
    public List<TextClassificationDataSampleEntity> findSamplesByClassifierId(final Integer classifierEntityId) {
        return query().select(textClassificationDataSampleEntity).from(textClassificationDataSampleEntity).where(
                textClassificationDataSampleEntity.classifierId.eq(classifierEntityId)).fetch();
    }
}
