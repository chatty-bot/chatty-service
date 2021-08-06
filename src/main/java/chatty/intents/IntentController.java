package chatty.intents;


import java.util.List;
import javax.inject.Inject;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

/**
 * I'm the rest endpoint as entrypoint for intent logic
 */
@Controller("/intents")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class IntentController {

   @Inject
   private IntentFacade intentFacade;

    /**
     * Create a new intent based on a list of strings and the corresponding classifier name
     *
     * @param intents        the inputs that will be new intents
     * @param classifierName the corresponding classifier name
     * @return a list of all newly created intents
     */
    @Post(value = "/persistAll", produces = MediaType.APPLICATION_JSON)
    public List<IntentTO> createIntent(final List<String> intents, String classifierName) {
        return intentFacade.createIntentsOfStrings(intents, classifierName);
    }


    /**
     * Fetch all available intents based on a classifier
     *
     * @param classifierName the classifier name that contains multiple intents (potentially)
     * @return a list of strings that are intents for the input classifier
     */
    @Get(produces = MediaType.APPLICATION_JSON)
    public List<IntentTO> getAllIntentsForClassifier(@QueryValue final String classifierName) {
        return intentFacade.findIntentsByClassifierName(classifierName);

    }


    /**
     * Create a single intent
     *
     * @param intentTO the intent that will be created
     * @return the newly created intent
     */
    @Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public IntentTO createSingleIntent(final IntentTO intentTO) {
        return intentFacade.createIntent(intentTO);

    }


}
