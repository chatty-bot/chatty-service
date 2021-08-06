package chatty_woz_data.symptoms;

import javax.inject.Singleton;
import org.modelmapper.ModelMapper;
import chatty.util.AbstractMapper;

@Singleton
public class SymptomMapper extends AbstractMapper<SymptomEntity, SymptomTO> {
    @Override
    public Class<SymptomEntity> getSourceClass() {
        return SymptomEntity.class;
    }


    @Override
    public Class<SymptomTO> getTargetClass() {
        return SymptomTO.class;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {

    }
}
