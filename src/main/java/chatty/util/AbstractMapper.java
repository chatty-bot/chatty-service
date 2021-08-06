package chatty.util;


import javax.annotation.PostConstruct;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public abstract class AbstractMapper<SOURCE, TARGET> {
    private ModelMapper MODEL_MAPPER = new ModelMapper();


    @PostConstruct
    public void setup() {
        this.MODEL_MAPPER.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        this.MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.configureMappings(this.MODEL_MAPPER);
    }


    public TARGET toTO(final SOURCE source) {
        return MODEL_MAPPER.map(source, getTargetClass());
    }


    public SOURCE toBE(final TARGET source) {
        return MODEL_MAPPER.map(source, getSourceClass());
    }


    public abstract Class<SOURCE> getSourceClass();

    public abstract Class<TARGET> getTargetClass();


    public abstract void configureMappings(final ModelMapper mapper);


    public ModelMapper getMapper() {
        return this.MODEL_MAPPER;
    }
}
