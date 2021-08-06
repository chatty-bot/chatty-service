package chatty.answer_selection;

import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import chatty.answer_selection.types.AnswerSelectionEntity;
import chatty.answer_selection.types.QAnswerSelectionEntity;
import chatty.util.AbstractRepository;
import chatty.util.exceptions.ChattyException;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.answer_selection.types.QAnswerSelectionEntity.answerSelectionEntity;

@Singleton
public class AnswerSelectionRepository extends AbstractRepository<AnswerSelectionEntity> {
    @Override
    protected Class<AnswerSelectionEntity> getEntityClass() {
        return AnswerSelectionEntity.class;
    }


    @Transactional(readOnly = true)
    public AnswerSelectionEntity findByAnswerSelectionName(final String answerSelectionModuleName) {
        final Optional<AnswerSelectionEntity> answerSelectionEntity = Optional.ofNullable(
                query().select(QAnswerSelectionEntity.answerSelectionEntity).from(
                        QAnswerSelectionEntity.answerSelectionEntity).where(
                        QAnswerSelectionEntity.answerSelectionEntity.answerSelectionName.eq(
                                answerSelectionModuleName)).fetchFirst());

        return answerSelectionEntity.orElseThrow(() ->
                new ChattyException("Could not find answer selection module with name " + answerSelectionModuleName));
    }


    @Transactional(readOnly = true)
    public List<AnswerSelectionEntity> findAllByUserId(final Integer userId) {
        return query().select(answerSelectionEntity).from(answerSelectionEntity).where(
                answerSelectionEntity.userEntity.id.eq(userId)).fetch();
    }
}
