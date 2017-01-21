package fr.univrouen.iataaaserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univrouen.iataaaserver.domain.request.WebSocketGameBean;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class SynchronizeHandlerIA extends TextWebSocketHandler {

    private WebSocketSession session;

    private ObjectMapper mapper= new ObjectMapper();

    public void gameChangement(WebSocketGameBean message) {
        if (session != null && session.isOpen()) {
            try {
                System.out.println("Send notification: " + mapper.writeValueAsString(message));
                session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Don't have open session to send");
        }
    }
    
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Connection established");
        this.session = session;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
            session.close();
        } else {
            System.out.println("Received:" + message.getPayload());
        }
    }

}
