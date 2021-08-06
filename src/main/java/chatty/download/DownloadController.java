package chatty.download;

import java.io.InputStream;
import javax.inject.Inject;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.server.types.files.StreamedFile;

@Controller("/download")
public class DownloadController {

    @Inject
    private DownloadService downloadService;


    @Get("/text_classification/{classifierName}")
    public StreamedFile download(@PathVariable final String classifierName) {
        InputStream inputStream = downloadService.getSamplesAsStreamForClassifier(classifierName);
        return new StreamedFile(inputStream, MediaType.TEXT_PLAIN_TYPE);
    }
}
