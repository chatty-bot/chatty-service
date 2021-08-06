package chatty.metrics;

import java.util.List;
import javax.inject.Inject;
import chatty.classifier.control.ClassifierService;
import chatty.classifier.types.ClassifierEntity;
import chatty.util.exceptions.ChattyException;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/metrics")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class MetricsController {


    @Inject
    private MetricsService metricsService;

    @Inject
    private ClassifierService classifierService;


    @Get("/{classifierName}")
    public List<MetricsEntity> findMetricsByClassifier(@PathVariable String classifierName) {
        final ClassifierEntity classifierEntity = classifierService.findClassifierByName(classifierName).orElseThrow(
                () -> new ChattyException("Classifier not found"));
        return metricsService.findMetricsByClassifierId(classifierEntity.getId());
    }
}
