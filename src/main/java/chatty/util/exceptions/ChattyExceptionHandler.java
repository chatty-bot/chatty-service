package chatty.util.exceptions;


import javax.inject.Singleton;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

@Singleton
@Produces
@Requires(classes = {ChattyException.class, ExceptionHandler.class})
public class ChattyExceptionHandler implements ExceptionHandler<ChattyException, HttpResponse> {
    @Override
    public HttpResponse handle(final HttpRequest httpRequest, final ChattyException exception) {
        return HttpResponse.badRequest(exception.getMessage());
    }


}
