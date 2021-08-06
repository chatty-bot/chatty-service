package chatty_woz_data.dialogue.control;

import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.modelmapper.ModelMapper;
import chatty.util.AbstractMapper;
import chatty_woz_data.dialogue.types.StateTO;
import chatty_woz_data.dialogue.types.SlotEntity;
import chatty_woz_data.dialogue.types.StateEntity;

@Singleton
public class StateMapper extends AbstractMapper<StateEntity, StateTO> {

    @Inject
    private SlotMapper slotMapper;


    @Override
    public Class<StateEntity> getSourceClass() {
        return StateEntity.class;
    }


    @Override
    public Class<StateTO> getTargetClass() {
        return StateTO.class;
    }


    @Override
    public StateEntity toBE(final StateTO source) {
        final StateEntity stateEntity = super.toBE(source);
        stateEntity.setSlots(source.getSlots().stream().map(it -> {
            final SlotEntity slotEntity = slotMapper.toBE(it);
            slotEntity.setStateEntity(stateEntity);
            return slotEntity;
        }).collect(Collectors.toList()));
        return stateEntity;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {

    }
}
