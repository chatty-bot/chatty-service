package chatty.chatbot;

import javax.inject.Inject;
import javax.inject.Singleton;
import chatty.gateway.ChatbotClient;
import chatty.gateway.types.ChatbotRequest;
import io.micronaut.http.HttpResponse;

@Singleton
public class ChatbotService {

    @Inject
    private ChatbotClient chatbotClient;


    public HttpResponse processRequest(final ChatbotRequest chatbotRequest) {
        return chatbotClient.processMessage(chatbotRequest);
    }
}
