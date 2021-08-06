package chatty.answer_selection.classifier;

import java.util.Arrays;
import javax.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import chatty.classifier.control.ClassifierMapper;
import chatty.classifier.types.ClassifierDataSampleTO;
import chatty.classifier.types.ClassifierEntity;
import chatty.classifier.types.ClassifierTO;
import chatty.intents.IntentEntity;
import chatty.upload.types.TextClassificationDataSampleEntity;
import chatty.user.UserEntity;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
public class ClassifierMapperTest {

    @Inject
    private ClassifierMapper underTest;


    @Test
    public void toDataSamplTO() {
        final TextClassificationDataSampleEntity entity = new TextClassificationDataSampleEntity();
        entity.setUserId(1);
        entity.setText("text");
        entity.setLabel("label");
        entity.setClassifierId(2);
        entity.setId(3);


        final ClassifierDataSampleTO result = underTest.toTO(entity);


        Assertions.assertThat(result.getClassifierId()).isEqualTo(2);
        Assertions.assertThat(result.getId()).isEqualTo(3);
        Assertions.assertThat(result.getUserId()).isEqualTo(1);
        Assertions.assertThat(result.getText()).isEqualTo("text");
        Assertions.assertThat(result.getLabel()).isEqualTo("label");
    }


    @Test
    public void toClassifierTO() {
        final ClassifierEntity entity = new ClassifierEntity();
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        entity.setUserEntity(userEntity);

        final IntentEntity intentEntity_0 = new IntentEntity();
        intentEntity_0.setNumberOfSamples(100);
        intentEntity_0.setClassifierEntity(entity);
        intentEntity_0.setIntentName("intent0");
        intentEntity_0.setId(1);
        final IntentEntity intentEntity_1 = new IntentEntity();
        intentEntity_1.setNumberOfSamples(300);
        intentEntity_1.setClassifierEntity(entity);
        intentEntity_1.setIntentName("intent1");
        intentEntity_1.setId(2);

        entity.setIntents(Arrays.asList(intentEntity_0, intentEntity_1));
        entity.setClassifierName("classifier");
        entity.setId(3);


        final ClassifierTO result = underTest.toTO(entity);


        Assertions.assertThat(result.getId()).isEqualTo(3);
        Assertions.assertThat(result.getUserId()).isEqualTo(1);
        Assertions.assertThat(result.getClassifierName()).isEqualTo("classifier");
        Assertions.assertThat(result.getIntents()).hasSize(2);


        Assertions.assertThat(result.getIntents().get(0).getClassifierId()).isEqualTo(3);
        Assertions.assertThat(result.getIntents().get(0).getId()).isEqualTo(1);
        Assertions.assertThat(result.getIntents().get(0).getNumberOfSamples()).isEqualTo(100);
        Assertions.assertThat(result.getIntents().get(0).getIntentName()).isEqualTo("intent0");

        Assertions.assertThat(result.getIntents().get(1).getClassifierId()).isEqualTo(3);
        Assertions.assertThat(result.getIntents().get(1).getId()).isEqualTo(2);
        Assertions.assertThat(result.getIntents().get(1).getNumberOfSamples()).isEqualTo(300);
        Assertions.assertThat(result.getIntents().get(1).getIntentName()).isEqualTo("intent1");


    }
}
