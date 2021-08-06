package chatty.gateway;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.dst.control.DstService;
import chatty.dst.types.DstEntity;
import chatty.dst.types.DstTO;
import chatty.user.UserEntity;
import chatty.user.UserService;
import chatty.util.exceptions.ChattyException;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.multipart.MultipartBody;
import io.micronaut.http.multipart.CompletedFileUpload;

@Singleton
public class DialogueStateTrackingGatewayService {

    @Inject
    private ChattyNLPClient chattyNLPClient;

    @Inject
    private UserService userService;

    @Inject
    private DstService dstService;

    @Property(name = "dst.prefix")
    private String prefix;


    public void trainDst(final Integer epochs, final String dstId, final String userName) {
        final DstEntity dstEntity = dstService.findById(dstId);
        final UserEntity userEntity = userService.findByUserName(userName).orElseThrow(
                () -> new ChattyException("User does not exist"));
        chattyNLPClient.trainDialogueStateTracking(epochs, dstEntity.getFileName(), String.valueOf(userEntity.getId()),
                String.valueOf(dstEntity.getId()));
    }


    public HttpResponse predictDst(final String transcript, final List<String> systemActs,
                                   final Map<String, Map<String, String>> beliefState, final String dstId,
                                   final String userName) {
        final DstEntity dstEntity = dstService.findById(dstId);
        final UserEntity userEntity = userService.findByUserName(userName).orElseThrow(
                () -> new ChattyException("User does not exist"));
        return chattyNLPClient.predictDialogueStateTracking(transcript, systemActs, beliefState,
                dstEntity.getFileName(),
                String.valueOf(userEntity.getId()),
                String.valueOf(dstEntity.getId()));

    }


    public DstTO uploadDst(final String dstName, final CompletedFileUpload completedFileUpload, final String userName) {
        final UserEntity userEntity = userService.findByUserName(userName).orElseThrow(
                () -> new ChattyException("User does not exist"));


        File file = null;
        try {
            file = File.createTempFile("data", null);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(completedFileUpload.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String generatedFileName = generateFileName(dstName, userEntity.getId()) + ".json";

        MultipartBody requestBody = MultipartBody.builder()
                .addPart(
                        "file",
                        generatedFileName,
                        MediaType.TEXT_PLAIN_TYPE,
                        file
                ).build();
        this.chattyNLPClient.uploadDst(requestBody);

        final DstTO dstTO = new DstTO();
        dstTO.setFileName(generatedFileName);
        dstTO.setDstName(dstName);
        dstTO.setTrain(false);
        dstTO.setUserId(userEntity.getId());

        return dstService.createNewDst(userEntity, dstTO);
    }


    private String generateFileName(final String dstname, final int id) {
        return String.join("_", prefix, dstname, String.valueOf(id));
    }
}
