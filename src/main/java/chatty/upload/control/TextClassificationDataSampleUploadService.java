package chatty.upload.control;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.opencsv.CSVReader;
import chatty.classifier.entity.ClassifierRepository;
import chatty.classifier.types.ClassifierEntity;
import chatty.intents.IntentEntity;
import chatty.intents.IntentService;
import chatty.upload.control.annotations.TextClassificationUpload;
import chatty.upload.entity.TextClassificationDataSampleRepository;
import chatty.upload.types.TextClassificationDataSampleEntity;
import chatty.upload.types.TextClassificationDatasetEntity;
import chatty.user.UserEntity;
import chatty.user.UserRepository;
import chatty.util.exceptions.ChattyException;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.upload.boundary.UploadController.SKIP_HEADERS;

@TextClassificationUpload
@Singleton
public class TextClassificationDataSampleUploadService implements UploadService {

    @Inject
    private TextClassificationDataSampleRepository textClassificationDataSampleRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ClassifierRepository classifierRepository;

    @Inject
    private IntentService intentService;

    @Inject
    private TextClassificationDatasetService textClassificationDatasetService;


    @Property(name = "headers.classification")
    private List<String> headers;


    @Override
    @Transactional
    public void process(final String classifierName, final String userName, final CompletedFileUpload file) {
        try {
            UserEntity userEntity = userRepository.findByUserName(userName).orElseThrow(() ->
                    new RuntimeException("User not found"));
            ClassifierEntity classifierEntity = classifierRepository.findByClassifierName(classifierName).orElseThrow(
                    () -> new RuntimeException("Classifier not found"));
            String[] line;
            // check if is csv or json

            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
            List<String> csvHeaders = new ArrayList<>();
            if (!SKIP_HEADERS) {
                csvHeaders = Arrays.asList(csvReader.readNext());
                if (!csvHeaders.containsAll(headers)) {
                    throw new ChattyException("Wrong .csv format, need to have text and label field");
                }
            }
            //TODO not sure if property mapping is always in the same order, otherwise use a Map<String,Integer>
            int textIndex = csvHeaders.indexOf(headers.get(0));
            int labelIndex = csvHeaders.indexOf(headers.get(1));

            ArrayList<TextClassificationDataSampleEntity> entities = new ArrayList<>();
            final HashMap<String, Integer> newIntents = new HashMap<>();
            while ((line = csvReader.readNext()) != null) {
                TextClassificationDataSampleEntity textClassificationDataSampleEntity = new TextClassificationDataSampleEntity();
                textClassificationDataSampleEntity.setText(line[textIndex]);

                final String label = line[labelIndex];
                if (newIntents.containsKey(label)) {
                    newIntents.put(label, newIntents.get(label) + 1);
                } else {
                    newIntents.put(label, 1);
                }
                textClassificationDataSampleEntity.setLabel(label);
                textClassificationDataSampleEntity.setUserId(userEntity.getId());
                textClassificationDataSampleEntity.setClassifierId(classifierEntity.getId());
                entities.add(textClassificationDataSampleEntity);

            }

            final TextClassificationDatasetEntity textClassificationDatasetEntity = new TextClassificationDatasetEntity();
            textClassificationDatasetEntity.setClassifierId(classifierEntity.getId());
            textClassificationDatasetEntity.setFileName(file.getFilename());
            textClassificationDatasetEntity.setNumberOfSamples(entities.size());

            textClassificationDatasetService.persist(textClassificationDatasetEntity);

            final List<IntentEntity> intentEntities = new ArrayList<>();
            for (Map.Entry entry : newIntents.entrySet()) {
                final IntentEntity intentEntity = new IntentEntity();
                intentEntity.setIntentName((String) entry.getKey());
                intentEntity.setNumberOfSamples((Integer) entry.getValue());
                intentEntities.add(intentEntity);

            }
            intentService.createIntents(intentEntities, classifierEntity);
            textClassificationDataSampleRepository.batchInsert(entities.iterator(), 500);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Transactional
    public TextClassificationDataSampleEntity persist(final TextClassificationDataSampleEntity sampleEntity) {
        return textClassificationDataSampleRepository.persist(sampleEntity);
    }
}
