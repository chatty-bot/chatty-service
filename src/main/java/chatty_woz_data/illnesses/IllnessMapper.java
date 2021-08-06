package chatty_woz_data.illnesses;

import javax.inject.Singleton;
import org.modelmapper.ModelMapper;
import chatty.util.AbstractMapper;

@Singleton
public class IllnessMapper extends AbstractMapper<IllnessEntity, IllnessTO> {


    @Override
    public Class<IllnessEntity> getSourceClass() {
        return IllnessEntity.class;
    }


    @Override
    public Class<IllnessTO> getTargetClass() {
        return IllnessTO.class;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {

    }


}
