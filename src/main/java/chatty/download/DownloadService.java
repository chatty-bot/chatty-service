package chatty.download;

import java.io.InputStream;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.classifier.control.ClassifierService;
import chatty.classifier.types.ClassifierEntity;
import chatty.upload.entity.TextClassificationDataSampleRepository;
import chatty.upload.types.TextClassificationDataSampleEntity;
import chatty.util.exceptions.ChattyException;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class DownloadService {


    @Inject
    private TextClassificationDataSampleRepository textClassificationDataSampleRepository;

    @Inject
    private ClassifierService classifierService;


    @Transactional(readOnly = true)
    public InputStream getSamplesAsStreamForClassifier(final String classifierName) {
        final ClassifierEntity classifierEntity = classifierService.findClassifierByName(classifierName).orElseThrow(
                () -> new ChattyException("Could not find classifier with name " + classifierName));
        final List<TextClassificationDataSampleEntity> samplesByClassifierId = textClassificationDataSampleRepository.findSamplesByClassifierId(
                classifierEntity.getId());
        // TODO
        return null;
    }


}
