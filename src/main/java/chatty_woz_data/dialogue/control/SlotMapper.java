package chatty_woz_data.dialogue.control;

import javax.inject.Singleton;
import org.modelmapper.ModelMapper;
import chatty.util.AbstractMapper;
import chatty_woz_data.dialogue.types.SlotEntity;
import chatty_woz_data.dialogue.types.SlotTO;

@Singleton
public class SlotMapper extends AbstractMapper<SlotEntity, SlotTO> {
    @Override
    public Class<SlotEntity> getSourceClass() {
        return SlotEntity.class;
    }


    @Override
    public Class<SlotTO> getTargetClass() {
        return SlotTO.class;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {

    }
}
