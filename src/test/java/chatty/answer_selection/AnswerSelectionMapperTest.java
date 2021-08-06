package chatty.answer_selection;


import java.util.Collections;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import chatty.answer_selection.types.AnswerSelectionEntity;
import chatty.answer_selection.types.AnswerSelectionTO;
import chatty.user.UserEntity;
import io.micronaut.test.annotation.MicronautTest;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class AnswerSelectionMapperTest {

    @Inject
    private AnswerSelectionMapper underTest;


    @Test
    public void toTO() {
        AnswerSelectionEntity answerSelectionEntity = new AnswerSelectionEntity();
        answerSelectionEntity.setAnswerSelectionName("test");
        answerSelectionEntity.setId(1);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(5);

        answerSelectionEntity.setUserEntity(userEntity);


        final AnswerSelectionTO answerSelectionTO = underTest.toTO(answerSelectionEntity, Collections.emptyList());


        assertThat(answerSelectionTO.getAnswerSelectionName()).isEqualTo("test");
        assertThat(answerSelectionTO.getUserId()).isEqualTo(5);
        assertThat(answerSelectionTO.getId()).isEqualTo(1);
        assertThat(answerSelectionTO.getClassifiers()).isEmpty();
    }
}
