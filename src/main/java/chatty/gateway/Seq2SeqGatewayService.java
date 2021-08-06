package chatty.gateway;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.seq2seq.Seq2SeqService;
import chatty.seq2seq.Seq2SeqTO;
import chatty.user.UserEntity;
import chatty.user.UserService;
import chatty.util.exceptions.ChattyException;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.multipart.MultipartBody;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class Seq2SeqGatewayService {

    @Inject
    private UserService userService;

    @Inject
    private ChattyNLPClient chattyNLPClient;

    @Inject
    private Seq2SeqService seq2SeqService;


    @Property(name = "seq2seq.prefix")
    private String prefix;


    @Transactional
    public Seq2SeqTO trainSeq2Seq(final int epochs, final String seq2SeqName, final String username,
                                  final String seq2SeqId) {

        final UserEntity userEntity = userService.findByUserName(username).orElseThrow(
                () -> new ChattyException("User does not exist"));
        final String generatedFileName = generateFileName(seq2SeqName, userEntity.getId()) + ".json";
        this.chattyNLPClient.trainSeq2Seq(epochs, generatedFileName, seq2SeqId);

        return seq2SeqService.updateSeq2Seq(seq2SeqName, true);
    }


    public String predictSeq2Seq(final String transcript, final String seq2SeqId) {
        return this.chattyNLPClient.predictSeq2Seq(transcript, seq2SeqId);
    }


    public Seq2SeqTO uploadSeq2Seq(final String seq2SeqName, final CompletedFileUpload completedFileUpload,
                                   final String username) {
        final UserEntity userEntity = userService.findByUserName(username).orElseThrow(
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
        final String generatedFileName = generateFileName(seq2SeqName, userEntity.getId()) + ".json";

        MultipartBody requestBody = MultipartBody.builder()
                .addPart(
                        "file",
                        generatedFileName,
                        MediaType.TEXT_PLAIN_TYPE,
                        file
                ).build();
        this.chattyNLPClient.uploadSeq2Seq(requestBody);
        return seq2SeqService.createSeq2SeqForUser(seq2SeqName, generatedFileName, userEntity);


    }


    private String generateFileName(final String seq2SeqName, final int id) {
        return String.join("_", prefix, seq2SeqName, String.valueOf(id));
    }
}
