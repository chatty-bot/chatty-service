package chatty_woz_data.dialogue.control;

import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.modelmapper.ModelMapper;
import chatty.util.AbstractMapper;
import chatty_woz_data.dialogue.types.DialogueDataEntity;
import chatty_woz_data.dialogue.types.DialogueEntity;
import chatty_woz_data.dialogue.types.DialogueTO;


@Singleton
public class DialogueMapper extends AbstractMapper<DialogueEntity, DialogueTO> {

    @Inject
    private DialogueDataMapper dialogueDataMapper;


    @Override
    public Class<DialogueEntity> getSourceClass() {
        return DialogueEntity.class;
    }


    @Override
    public Class<DialogueTO> getTargetClass() {
        return DialogueTO.class;
    }


    @Override
    public DialogueEntity toBE(final DialogueTO source) {
        final DialogueEntity dialogueEntity = super.toBE(source);
        dialogueEntity.setDialogData(
                source.getDialogData().stream().map(it -> {
                    final DialogueDataEntity dialogueDataEntity = dialogueDataMapper.toBE(it);
                    dialogueDataEntity.setDialogueEntity(dialogueEntity);
                    return dialogueDataEntity;
                }).collect(Collectors.toList()));

        return dialogueEntity;
    }


    @Override
    public void configureMappings(final ModelMapper mapper) {

    }
}
