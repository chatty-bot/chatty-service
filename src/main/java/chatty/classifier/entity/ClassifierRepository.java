package chatty.classifier.entity;

import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import chatty.classifier.types.ClassifierEntity;
import chatty.util.AbstractRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.classifier.types.QClassifierEntity.classifierEntity;

@Singleton
public class ClassifierRepository extends AbstractRepository<ClassifierEntity> {
    @Override
    protected Class<ClassifierEntity> getEntityClass() {
        return ClassifierEntity.class;
    }


    @Transactional(readOnly = true)
    public Optional<ClassifierEntity> findByClassifierName(final String classifierName) {
        return Optional.ofNullable(query().select(classifierEntity).from(classifierEntity).where(
                classifierEntity.classifierName.eq(classifierName)).fetchFirst());
    }


    @Transactional(readOnly = true)
    public List<ClassifierEntity> findAllByUser(final String userName) {
        return query().from(classifierEntity).where(classifierEntity.userEntity.username.eq(userName)).fetch();
    }
}
