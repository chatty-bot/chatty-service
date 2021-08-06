package chatty.upload.entity;

import java.util.Iterator;
import javax.inject.Singleton;
import chatty.upload.types.AnswerSelectionDataSampleEntity;
import chatty.util.AbstractRepository;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class AnswerSelectionDataSampleRepository extends AbstractRepository<AnswerSelectionDataSampleEntity> {
    @Override
    protected Class<AnswerSelectionDataSampleEntity> getEntityClass() {
        return AnswerSelectionDataSampleEntity.class;
    }


    @Transactional
    public void batchInsert(Iterator<AnswerSelectionDataSampleEntity> dataSource, final int batchSize) {
        int count = 0;
        while (dataSource.hasNext()) {
            AnswerSelectionDataSampleEntity entity = dataSource.next();
            persist(entity);
            count++;
            if (count % batchSize == 0) {
                getEntityManager().flush();
                getEntityManager().clear();

            }
        }

    }
}
