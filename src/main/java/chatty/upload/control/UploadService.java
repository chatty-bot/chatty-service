package chatty.upload.control;

import io.micronaut.http.multipart.CompletedFileUpload;

/**
 *
 */
public interface UploadService {

    void process(String moduleName, String userName, CompletedFileUpload file);


}
