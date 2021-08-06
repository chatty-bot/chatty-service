package chatty.classifier.control;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import chatty.classifier.types.ClassifierDataSampleTO;
import chatty.classifier.types.ClassifierEntity;
import chatty.classifier.types.ClassifierTO;
import chatty.intents.IntentMapper;
import chatty.intents.IntentTO;
import chatty.upload.types.TextClassificationDataSampleEntity;

@Singleton
public class ClassifierMapper {
    private ModelMapper MODEL_MAPPER = new ModelMapper();

    @Inject
    private IntentMapper intentMapper;


    @PostConstruct
    public void setup() {
        this.MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.MODEL_MAPPER.getConfiguration().setPropertyCondition(Conditions.isNotNull());

    }


    public ClassifierDataSampleTO toTO(final TextClassificationDataSampleEntity source) {
        return MODEL_MAPPER.map(source, ClassifierDataSampleTO.class);
    }


    public ClassifierTO toTO(final ClassifierEntity source) {
        final ClassifierTO classifierTO = MODEL_MAPPER.map(source, ClassifierTO.class);
        final List<IntentTO> collect = source.getIntents().stream().map(intentMapper::toTO).collect(
                Collectors.toList());
        classifierTO.setIntents(collect);
        return classifierTO;
    }
}
