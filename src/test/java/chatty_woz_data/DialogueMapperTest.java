package chatty_woz_data;

import java.util.Collections;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import chatty_woz_data.dialogue.types.SlotTO;
import chatty_woz_data.dialogue.types.StateTO;
import chatty_woz_data.dialogue.control.DialogueMapper;
import chatty_woz_data.dialogue.types.DialogueDataTO;
import chatty_woz_data.dialogue.types.DialogueEntity;
import chatty_woz_data.dialogue.types.DialogueTO;
import io.micronaut.test.annotation.MicronautTest;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class DialogueMapperTest {

    @Inject
    private DialogueMapper underTest;


    @Test
    public void toBE() {
        final DialogueTO dialogueTO = new DialogueTO();
        final DialogueDataTO dialogueDataTO = new DialogueDataTO();
        final StateTO stateTO = new StateTO();
        final SlotTO slotTO = new SlotTO();
        slotTO.setLabel("label");
        slotTO.setValue("value");
        slotTO.setStateId(1);

        stateTO.setSlots(Collections.singletonList(slotTO));
        dialogueDataTO.setSystemTranscript("systemtranscript");
        dialogueDataTO.setStateTO(stateTO);
        dialogueDataTO.setTurn(0);
        dialogueDataTO.setTranscript("transcript");
        dialogueTO.setDialogData(Collections.singletonList(dialogueDataTO));

        final DialogueEntity dialogueEntity = underTest.toBE(dialogueTO);


        assertThat(dialogueEntity.getDialogData()).hasSize(1);
        assertThat(dialogueEntity.getDialogData().get(0).getSystemTranscript()).isEqualTo("systemtranscript");
        assertThat(dialogueEntity.getDialogData().get(0).getTranscript()).isEqualTo("transcript");
        assertThat(dialogueEntity.getDialogData().get(0).getTurn()).isEqualTo(0);
        assertThat(dialogueEntity.getDialogData().get(0).getStateEntity().getSlots()).hasSize(1);
        assertThat(dialogueEntity.getDialogData().get(0).getStateEntity().getSlots().get(0).getLabel()).isEqualTo(
                "label");
        assertThat(dialogueEntity.getDialogData().get(0).getStateEntity().getSlots().get(0).getValue()).isEqualTo(
                "value");
    }

}
