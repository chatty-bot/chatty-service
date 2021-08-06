package chatty.answer_selection;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import chatty.answer_selection.types.AnswerSelectionEntity;
import chatty.answer_selection.types.AnswerSelectionTO;
import chatty.classifier.types.ClassifierTO;
import chatty.util.AbstractMapper;

/**
 * I'm the mapper that converts answer selection database entities {@link AnswerSelectionEntity} to frontend objects {@link AnswerSelectionTO}
 */
@Singleton
public class AnswerSelectionMapper extends AbstractMapper<AnswerSelectionEntity, AnswerSelectionTO> {


    private ModelMapper MODEL_MAPPER = new ModelMapper();


    /**
     * Map a given answer selectio entity and the corresponding classifiers of the current user to a frontend object.
     *
     * @param answerSelectionEntity the entity to map
     * @param classifiers           all corresponding classifiers that the current user has created
     * @return the mapped frontend object {@link AnswerSelectionTO}
     */
    public AnswerSelectionTO toTO(final AnswerSelectionEntity answerSelectionEntity,
                                  final List<ClassifierTO> classifiers) {
        final AnswerSelectionTO answerSelectionTO = MODEL_MAPPER.map(answerSelectionEntity, AnswerSelectionTO.class);
        answerSelectionTO.setClassifiers(classifiers);
        return answerSelectionTO;

    }


    @PostConstruct
    public void setup() {

        this.MODEL_MAPPER.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        this.MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.MODEL_MAPPER.typeMap(AnswerSelectionEntity.class, AnswerSelectionTO.class)
                .addMapping(src -> src.getUserEntity().getId(), AnswerSelectionTO::setUserId);
    }


    @Override
    public Class<AnswerSelectionEntity> getSourceClass() {
        return AnswerSelectionEntity.class;
    }


    @Override
    public Class<AnswerSelectionTO> getTargetClass() {
        return AnswerSelectionTO.class;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {

    }


}
