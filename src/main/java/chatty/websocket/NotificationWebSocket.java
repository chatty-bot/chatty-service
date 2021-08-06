package chatty.websocket;

import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;

@ServerWebSocket("ws/{id}")
public class NotificationWebSocket {
    private WebSocketBroadcaster webSocketBroadCaster;


    @OnOpen
    private void onOpen(final String id, WebSocketSession session) {
        
    }

}
