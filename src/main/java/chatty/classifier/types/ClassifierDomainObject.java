package chatty.classifier.types;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import chatty.intents.IntentTO;
import chatty.metrics.MetricsTO;
import chatty.training.TrainingEntity;
import chatty.upload.types.TextClassificationDatasetEntity;

/**
 * I'm the domain object for the frontend created of a classifier an all objects that are related to this classifier.
 */
public class ClassifierDomainObject {
    @NotBlank
    private String classifierName;

    @NotNull
    private List<MetricsTO> metrics = new ArrayList<>();
    @NotNull
    private List<IntentTO> intents = new ArrayList<>();
    @NotNull
    private List<TextClassificationDatasetEntity> datasets = new ArrayList<>();
    @NotNull
    private List<TrainingEntity> trainings = new ArrayList<>();


    public String getClassifierName() {
        return classifierName;
    }


    public void setClassifierName(final String classifierName) {
        this.classifierName = classifierName;
    }


    public List<MetricsTO> getMetrics() {
        return metrics;
    }


    public void setMetrics(final List<MetricsTO> metrics) {
        this.metrics = metrics;
    }


    public List<IntentTO> getIntents() {
        return intents;
    }


    public void setIntents(final List<IntentTO> intents) {
        this.intents = intents;
    }


    public List<TextClassificationDatasetEntity> getDatasets() {
        return datasets;
    }


    public void setDatasets(final List<TextClassificationDatasetEntity> datasets) {
        this.datasets = datasets;
    }


    public void setTrainings(final List<TrainingEntity> trainings) {
        this.trainings = trainings;
    }


    public List<TrainingEntity> getTrainings() {
        return trainings;
    }
}
