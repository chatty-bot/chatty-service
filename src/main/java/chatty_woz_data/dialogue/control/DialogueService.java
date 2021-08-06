package chatty_woz_data.dialogue.control;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.util.exceptions.ChattyException;
import chatty_woz_data.dialogue.entity.DialogueDataRepository;
import chatty_woz_data.dialogue.entity.DialogueRepository;
import chatty_woz_data.dialogue.types.DialogueDataEntity;
import chatty_woz_data.dialogue.types.DialogueEntity;
import chatty_woz_data.dialogue.types.DialogueTO;
import chatty_woz_data.dialogue.types.DialogueUpdateTO;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class DialogueService {

    @Inject
    private DialogueMapper dialogueMapper;

    @Inject
    private DialogueDataMapper dialogueDataMapper;

    @Inject
    private DialogueRepository dialogueRepository;

    @Inject
    private DialogueDataRepository dialogueDataRepository;


    @Transactional
    public void createDialogue(final DialogueTO dialogueTO) {
        final DialogueEntity dialogueEntity = dialogueMapper.toBE(dialogueTO);
        dialogueRepository.persist(dialogueEntity);

    }


    @Transactional
    public DialogueTO findLatestDialogueRequiresSystem() {
        /**
         * I want to select a dialog which is not yet finshed and requires
         */
        final Optional<DialogueEntity> notFinishedAndLastTurnIsUser = dialogueRepository.findNotFinishedAndRequiresSystem();
        return notFinishedAndLastTurnIsUser.map(dialogueEntity -> dialogueMapper.toTO((dialogueEntity))).orElse(null);

    }


    @Transactional
    public DialogueTO findLatestDialogueRequiresUser() {
        final Optional<DialogueEntity> notFinishedAndLastTurnIsUser = dialogueRepository.findNotFinishedAndRequiresUser();
        return notFinishedAndLastTurnIsUser.map(dialogueEntity -> dialogueMapper.toTO((dialogueEntity))).orElse(null);


    }


    @Transactional
    public void addDialogueDataToDialogue(final DialogueUpdateTO dialogueUpdateTO) {
       
        // TODO update dialogs

        final Optional<DialogueEntity> dialogueEntityOptional = dialogueRepository.findById(
                dialogueUpdateTO.getDialogId());
        if (!dialogueEntityOptional.isPresent()) {
            throw new ChattyException("Dialogue with id " + dialogueUpdateTO.getDialogId() + " not fonud");
        }
        DialogueEntity entity = dialogueEntityOptional.get();
        if (!dialogueUpdateTO.isFinished()) {
            final DialogueDataEntity dialogueDataEntity = dialogueDataMapper.toBE(dialogueUpdateTO.getDialogueDataTO());
            entity.addDialogData(dialogueDataEntity);
            entity.setRequiresActionFrom(dialogueUpdateTO.getRequiresActionFrom());
            dialogueRepository.merge(entity, true);
        } else {
            entity.setFinished(dialogueUpdateTO.isFinished());
            dialogueRepository.merge(entity, true);
        }


    }


    @Transactional
    public void updateDialogueDataInDialogue(final DialogueUpdateTO dialogueUpdateTO) {
        // TODO update dialogs
        final Optional<DialogueEntity> dialogueEntityOptional = dialogueRepository.findById(
                dialogueUpdateTO.getDialogId());
        if (!dialogueEntityOptional.isPresent()) {
            throw new ChattyException("Dialogue with id " + dialogueUpdateTO.getDialogId() + " not fonud");
        }
        DialogueEntity dialogueEntity = dialogueEntityOptional.get();
        final Optional<DialogueDataEntity> first = dialogueEntity.getDialogData().stream().filter(
                it -> it.getTurn().equals(dialogueUpdateTO.getDialogueDataTO().getTurn())).findFirst();
        if (!first.isPresent()) {
            throw new ChattyException("Dialogue data des not exist in dialogue");
        }

        DialogueDataEntity updatedDialogue = first.get();
        updatedDialogue.setTranscript(dialogueUpdateTO.getDialogueDataTO().getTranscript());
        dialogueEntity.setRequiresActionFrom(dialogueUpdateTO.getRequiresActionFrom());

        final DialogueEntity merge = dialogueRepository.merge(dialogueEntity, true);
    }
}
