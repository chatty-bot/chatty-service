package chatty.classifier.boundary;

import java.security.Principal;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import chatty.classifier.control.ClassifierMapper;
import chatty.classifier.control.ClassifierService;
import chatty.classifier.types.ClassifierDataSampleTO;
import chatty.classifier.types.ClassifierDomainObject;
import chatty.classifier.types.ClassifierEntity;
import chatty.classifier.types.ClassifierTO;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

/**
 * Controller for managing request regarding classifiers
 */
@Controller("/classifier")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ClassifierController {

    @Inject
    private ClassifierService classifierService;

    @Inject
    private ClassifierMapper classifierMapper;


    /**
     * Create  a new classifier
     *
     * @param principal      the current user
     * @param classifierName the name of the classifier
     * @return the newly created classifier {@link ClassifierEntity}
     */
    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ClassifierTO createClassifier(Principal principal,
                                         @NotBlank String classifierName) {
        return classifierMapper.toTO(classifierService.create(principal.getName(), classifierName));
    }


    /**
     * Get all classifiers for the current user
     *
     * @param principal the curernt user
     * @return a list of classifiers that correspond to the current user
     */
    @Get(produces = MediaType.APPLICATION_JSON)
    public List<ClassifierTO> getAllClassifiersForUser(Principal principal) {
        return classifierService.findAllByUser(principal.getName());
    }


    /**
     * Fetch a single classifier that corresponds to a user
     *
     * @param classifierName the name of the classifier
     * @param principal      the current user
     * @return the fetched classifier
     */
    @Get("/{classifierName}")
    @Produces(MediaType.APPLICATION_JSON)
    public ClassifierDomainObject getClassifierForUser(@PathVariable @NotBlank final String classifierName,
                                                       final Principal principal) {
        return classifierService.extractClassifierDomainObject(
                classifierName);
    }


    /**
     * Persist a new data sample that belongs to a particular classifier
     *
     * @param classifierName the name of the classifier the new datasample will be added
     * @param dataSampleTO   the data sample with text adn label fields.
     * @param principal      the current user
     * @return the newly created data sample transport object {@link ClassifierDataSampleTO}
     */
    @Post(value = "/{classifierName}/save_sample", consumes = MediaType.APPLICATION_JSON)
    public ClassifierDataSampleTO createDataSampleForClassifier(@PathVariable @NotBlank final String classifierName,
                                                                @Valid final ClassifierDataSampleTO dataSampleTO,
                                                                final Principal principal) {
        return classifierMapper.toTO(
                classifierService.addDataSample(classifierName, dataSampleTO, principal.getName()));
    }
}
