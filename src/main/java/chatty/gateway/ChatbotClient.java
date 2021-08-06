package chatty.gateway;

import chatty.gateway.types.ChatbotRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

@Client("http://localhost:5000")
public interface ChatbotClient {


    @Post(value = "/chatbot", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse processMessage(ChatbotRequest chatbotRequest);
}
