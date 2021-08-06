package chatty.upload.control;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.opencsv.CSVReader;
import chatty.answer_selection.AnswerSelectionService;
import chatty.answer_selection.types.AnswerSelectionDataSampleTO;
import chatty.answer_selection.types.AnswerSelectionTO;
import chatty.upload.control.annotations.AnswerSelectionUpload;
import chatty.upload.types.AnswerSelectionDatasetEntity;
import chatty.user.UserEntity;
import chatty.user.UserService;
import chatty.util.exceptions.ChattyException;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.spring.tx.annotation.Transactional;
import static chatty.upload.boundary.UploadController.SKIP_HEADERS;


@AnswerSelectionUpload
@Singleton
public class AnswerSelectionUploadService implements UploadService {


    @Inject
    private UserService userService;

    @Inject
    private AnswerSelectionService answerSelectionService;

    @Inject
    private AnswerSelectionDatasetService answerSelectionDatasetService;


    @Inject
    private AnswerSelectionDataSampleService answerSelectionDataSampleService;

    @Property(name = "headers.answer-selection")
    private List<String> headers;


    @Override
    @Transactional
    public void process(final String moduleName, final String userName, final CompletedFileUpload file) {
        try {
            UserEntity userEntity = userService.findByUserName(userName).orElseThrow(() ->
                    new RuntimeException("User not found"));


            AnswerSelectionTO answerSelectionTO = answerSelectionService.findByAnswerSelectionName(
                    moduleName, userName);
            String[] line;
            // check if is csv or json

            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
            List<String> csvHeaders = new ArrayList<>();
            if (!SKIP_HEADERS) {
                csvHeaders = Arrays.asList(csvReader.readNext());
                if (!csvHeaders.containsAll(headers)) {
                    throw new ChattyException("Wrong .csv format, need to have text and response field");
                }
            }
            int textIndex = csvHeaders.indexOf(headers.get(0));
            int responseIndex = csvHeaders.indexOf(headers.get(1));
            int taskIndex = csvHeaders.indexOf(headers.get(2));

            List<AnswerSelectionDataSampleTO> entities = new ArrayList<>();
            while ((line = csvReader.readNext()) != null) {
                AnswerSelectionDataSampleTO answerSelectionDataSampleTO = new AnswerSelectionDataSampleTO();
                answerSelectionDataSampleTO.setText(line[textIndex]);

                final String response = line[responseIndex];
                final String task = line[taskIndex];

                answerSelectionDataSampleTO.setResponse(response);
                answerSelectionDataSampleTO.setIntentName(task);
                answerSelectionDataSampleTO.setUserId(userEntity.getId());
                answerSelectionDataSampleTO.setAnswerSelectionId(answerSelectionTO.getId());
                entities.add(answerSelectionDataSampleTO);

            }

            final AnswerSelectionDatasetEntity answerSelectionDatasetEntity = new AnswerSelectionDatasetEntity();
            answerSelectionDatasetEntity.setAnswerSelectionId(answerSelectionTO.getId());
            answerSelectionDatasetEntity.setFileName(file.getFilename());
            answerSelectionDatasetEntity.setNumberOfSamples(entities.size());

            answerSelectionDatasetService.persist(answerSelectionDatasetEntity);
            answerSelectionDataSampleService.persist(entities, userEntity.getId());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
