package chatty.upload.control.mappers;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import chatty.answer_selection.types.AnswerSelectionDataSampleTO;
import chatty.upload.types.AnswerSelectionDataSampleEntity;

@Singleton
public class AnswerSelectionDataSampleMapper {
    private ModelMapper MODEL_MAPPER = new ModelMapper();


    @PostConstruct
    public void setup() {

        this.MODEL_MAPPER.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        this.MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    public List<AnswerSelectionDataSampleEntity> toEntity(final List<AnswerSelectionDataSampleTO> sources,
                                                          final Integer userId) {
        return sources.stream().map(it -> {
            final AnswerSelectionDataSampleEntity answerSelectionDataSampleEntities = toEntity(it);
            answerSelectionDataSampleEntities.setUserId(userId);
            return answerSelectionDataSampleEntities;

        }).collect(Collectors.toList());
    }


    public AnswerSelectionDataSampleEntity toEntity(final AnswerSelectionDataSampleTO source) {
        return MODEL_MAPPER.map(source,
                AnswerSelectionDataSampleEntity.class);

    }


}
