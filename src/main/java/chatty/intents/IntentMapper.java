package chatty.intents;

import javax.inject.Singleton;
import org.modelmapper.ModelMapper;
import chatty.util.AbstractMapper;

@Singleton
public class IntentMapper extends AbstractMapper<IntentEntity, IntentTO> {
    @Override
    public Class<IntentEntity> getSourceClass() {
        return IntentEntity.class;
    }


    @Override
    public Class<IntentTO> getTargetClass() {
        return IntentTO.class;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {
        mapper.typeMap(IntentEntity.class, IntentTO.class)
                .addMapping(src -> src.getClassifierEntity().getId(), IntentTO::setClassifierId);
    }
}
