package chatty.intents;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.classifier.control.ClassifierService;
import chatty.classifier.types.ClassifierEntity;
import chatty.util.exceptions.ChattyException;

/**
 * Facade pattern to ensure no circular dependencies are introduced
 */
@Singleton
public class IntentFacade {

    @Inject
    private IntentService intentService;

    @Inject
    private ClassifierService classifierService;


    /**
     * @param intents
     * @param classifierName
     * @return
     */
    public List<IntentTO> createIntentsOfStrings(final List<String> intents, final String classifierName) {
        final ClassifierEntity classifierEntity = classifierService.findClassifierByName(classifierName).orElseThrow(
                () -> new ChattyException("Could not find classifier with name " + classifierName));
        return intentService.createIntentsOfStrings(intents, classifierEntity);
    }


    /**
     * @param classifierName
     * @return
     */
    public List<IntentTO> findIntentsByClassifierName(final String classifierName) {
        final ClassifierEntity classifierEntity = classifierService.findClassifierByName(classifierName).orElseThrow(
                () -> new ChattyException("Could not find classifier with name " + classifierName));
        return intentService.findIntentsByClassifierName(classifierEntity);
    }


    public IntentTO createIntent(final IntentTO intentTO) {
        return intentService.createIntent(intentTO);
    }
}
