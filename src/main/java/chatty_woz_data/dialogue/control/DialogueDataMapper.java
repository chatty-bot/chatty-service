package chatty_woz_data.dialogue.control;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.modelmapper.ModelMapper;
import chatty.util.AbstractMapper;
import chatty_woz_data.dialogue.types.StateEntity;
import chatty_woz_data.dialogue.types.DialogueDataEntity;
import chatty_woz_data.dialogue.types.DialogueDataTO;

@Singleton
public class DialogueDataMapper extends AbstractMapper<DialogueDataEntity, DialogueDataTO> {


    @Inject
    private StateMapper stateMapper;


    @Override
    public Class<DialogueDataEntity> getSourceClass() {
        return DialogueDataEntity.class;
    }


    @Override
    public Class<DialogueDataTO> getTargetClass() {
        return DialogueDataTO.class;
    }


    @Override
    public DialogueDataEntity toBE(final DialogueDataTO source) {
        final DialogueDataEntity dialogueDataEntity = super.toBE(source);
        final StateEntity stateEntity = stateMapper.toBE(source.getStateTO());
        stateEntity.setDialogueDataEntity(dialogueDataEntity);
        dialogueDataEntity.setStateEntity(stateEntity);
        return dialogueDataEntity;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {

    }
}
